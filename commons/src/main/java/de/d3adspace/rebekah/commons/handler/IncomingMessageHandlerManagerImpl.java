package de.d3adspace.rebekah.commons.handler;

import com.google.common.collect.Maps;
import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.packet.Packet;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class IncomingMessageHandlerManagerImpl implements
  IncomingMessageHandlerManager {

  /**
   * How many parameters a parameter method can have.
   */
  private static final int VALID_PARAMETER_COUNT = 2;

  /**
   * All packet handlers.
   */
  private final Map<IncomingMessageHandler, Map<Class<? extends Packet>, List<IncomingMessageConsumer>>> requestHandlers = Maps
    .newConcurrentMap();

  @Override
  public void registerMessageHandler(
    IncomingMessageHandler incomingMessageHandler
  ) {
    if (requestHandlers.containsKey(incomingMessageHandler)) {
      throw new IllegalStateException(
        "Packet handler " + incomingMessageHandler + " already registered.");
    }

    Map<Class<? extends Packet>, List<IncomingMessageConsumer>> packetConsumers = assemblePacketConsumers(
      incomingMessageHandler);
    requestHandlers.put(incomingMessageHandler, packetConsumers);
  }

  @Override
  public void unregisterMessageHandler(
    IncomingMessageHandler incomingMessageHandler
  ) {
    requestHandlers.remove(incomingMessageHandler);
  }

  @Override
  public boolean isMessageHandlerRegistered(
    IncomingMessageHandler incomingMessageHandler
  ) {
    return requestHandlers.containsKey(incomingMessageHandler);
  }

  @Override
  public void process(
    MessageContext messageContext,
    IncomingMessage incomingMessage
  ) {
    for (Map<Class<? extends Packet>, List<IncomingMessageConsumer>> packetConsumers : requestHandlers
      .values()) {
      List<IncomingMessageConsumer> consumers = packetConsumers
        .get(incomingMessage.getClass());
      if (consumers != null) {
        consumers.forEach(
          consumer -> consumer.accept(messageContext, incomingMessage));
      }
    }
  }

  private Map<Class<? extends Packet>, List<IncomingMessageConsumer>> assemblePacketConsumers(
    IncomingMessageHandler incomingMessageHandler
  ) {
    Class<? extends IncomingMessageHandler> packetHandlerClass = incomingMessageHandler
      .getClass();
    Method[] packetHandlerClassDeclaredMethods = packetHandlerClass
      .getDeclaredMethods();

    Map<Class<? extends Packet>, List<IncomingMessageConsumer>> requestConsumers = Maps
      .newConcurrentMap();

    for (Method packetHandlerClassDeclaredMethod : packetHandlerClassDeclaredMethods) {
      int parameterCount = packetHandlerClassDeclaredMethod.getParameterCount();
      if (parameterCount != VALID_PARAMETER_COUNT) {
        continue;
      }

      Class<?>[] parameterTypes = packetHandlerClassDeclaredMethod
        .getParameterTypes();
      if (!parameterTypes[0].isAssignableFrom(MessageContext.class)
        || !Packet.class
        .isAssignableFrom(parameterTypes[1])) {
        continue;
      }

      IncomingMessageConsumer incomingMessageConsumer = assembleRequestConsumer(
        incomingMessageHandler, packetHandlerClassDeclaredMethod);

      Class<? extends Packet> parameterType = (Class<? extends Packet>) parameterTypes[1];
      List<IncomingMessageConsumer> consumers = requestConsumers
        .get(parameterType);
      if (consumers == null) {
        consumers = new CopyOnWriteArrayList<>();
        requestConsumers.put(parameterType, new CopyOnWriteArrayList<>());
      }

      consumers.add(incomingMessageConsumer);
    }

    return requestConsumers;
  }

  private IncomingMessageConsumer assembleRequestConsumer(
    IncomingMessageHandler incomingMessageHandler, Method method
  ) {
    return new IncomingMessageConsumer(incomingMessageHandler, method);
  }
}
