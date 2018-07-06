package de.d3adspace.rebekah.commons.handler;

import com.google.common.collect.Maps;
import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.packet.Packet;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RequestHandlerManagerImpl implements RequestHandlerManager {

    /**
     * How many parameters a parameter method can have.
     */
    private static final int VALID_PARAMETER_COUNT = 2;

    /**
     * All packet handlers.
     */
    private final Map<RequestHandler, Map<Class<? extends Packet>, List<RequestConsumer>>> packetHandlers = Maps.newConcurrentMap();

    @Override
    public void registerPacketHandler(RequestHandler requestHandler) {
        if (packetHandlers.containsKey(requestHandler)) {
            throw new IllegalStateException("Packet handler " + requestHandler + " already registered.");
        }

        Map<Class<? extends Packet>, List<RequestConsumer>> packetConsumers = assemblePacketConsumers(requestHandler);
        packetHandlers.put(requestHandler, packetConsumers);
    }

    @Override
    public void unregisterPacketHandler(RequestHandler requestHandler) {
        packetHandlers.remove(requestHandler);
    }

    private Map<Class<? extends Packet>, List<RequestConsumer>> assemblePacketConsumers(RequestHandler requestHandler) {
        Class<? extends RequestHandler> packetHandlerClass = requestHandler.getClass();
        Method[] packetHandlerClassDeclaredMethods = packetHandlerClass.getDeclaredMethods();

        Map<Class<? extends Packet>, List<RequestConsumer>> packetConsumers = Maps.newConcurrentMap();

        for (Method packetHandlerClassDeclaredMethod : packetHandlerClassDeclaredMethods) {
            int parameterCount = packetHandlerClassDeclaredMethod.getParameterCount();
            if (parameterCount != VALID_PARAMETER_COUNT) {
                continue;
            }

            Class<?>[] parameterTypes = packetHandlerClassDeclaredMethod.getParameterTypes();
            System.out.println(Arrays.toString(parameterTypes));
            if (!parameterTypes[0].isAssignableFrom(RequestContext.class) || !Packet.class.isAssignableFrom(parameterTypes[1])) {
                continue;
            }

            RequestConsumer requestConsumer = assemblePacketConsumer(requestHandler, packetHandlerClassDeclaredMethod);

            List<RequestConsumer> consumers = packetConsumers.get(parameterTypes[1]);
            if (consumers == null) {
                consumers = new CopyOnWriteArrayList<>();
                packetConsumers.put((Class<? extends Packet>) parameterTypes[1], consumers);
            }

            consumers.add(requestConsumer);
        }

        return packetConsumers;
    }

    private RequestConsumer assemblePacketConsumer(RequestHandler requestHandler, Method method) {
        return new RequestConsumer(requestHandler, method);
    }

    @Override
    public void process(RequestContext requestContext, Packet packet) {
        for (Map<Class<? extends Packet>, List<RequestConsumer>> packetConsumers : packetHandlers.values()) {
            List<RequestConsumer> consumers = packetConsumers.get(packet.getClass());

            if (consumers == null) {
                continue;
            }

            for (RequestConsumer consumer : consumers) {
                consumer.accept(requestContext, packet);
            }
        }
    }
}
