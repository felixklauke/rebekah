package de.d3adspace.rebekah.commons.handler;

import com.google.common.collect.Maps;
import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.request.Request;

import java.lang.reflect.Method;
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
    private final Map<RequestHandler, Map<Class<? extends Packet>, List<RequestConsumer>>> requestHandlers = Maps.newConcurrentMap();

    @Override
    public void registerRequestHandler(RequestHandler requestHandler) {
        if (requestHandlers.containsKey(requestHandler)) {
            throw new IllegalStateException("Packet handler " + requestHandler + " already registered.");
        }

        Map<Class<? extends Packet>, List<RequestConsumer>> packetConsumers = assemblePacketConsumers(requestHandler);
        requestHandlers.put(requestHandler, packetConsumers);
    }

    @Override
    public void unregisterRequestHandler(RequestHandler requestHandler) {
        requestHandlers.remove(requestHandler);
    }

    @Override
    public boolean isRequestHandlerRegistered(RequestHandler requestHandler) {
        return requestHandlers.containsKey(requestHandler);
    }

    @Override
    public void process(RequestContext requestContext, Request request) {
        for (Map<Class<? extends Packet>, List<RequestConsumer>> packetConsumers : requestHandlers.values()) {
            List<RequestConsumer> consumers = packetConsumers.get(request.getClass());

            if (consumers == null) {
                continue;
            }

            for (RequestConsumer consumer : consumers) {
                consumer.accept(requestContext, request);
            }
        }
    }

    private Map<Class<? extends Packet>, List<RequestConsumer>> assemblePacketConsumers(RequestHandler requestHandler) {
        Class<? extends RequestHandler> packetHandlerClass = requestHandler.getClass();
        Method[] packetHandlerClassDeclaredMethods = packetHandlerClass.getDeclaredMethods();

        Map<Class<? extends Packet>, List<RequestConsumer>> requestConsumers = Maps.newConcurrentMap();

        for (Method packetHandlerClassDeclaredMethod : packetHandlerClassDeclaredMethods) {
            int parameterCount = packetHandlerClassDeclaredMethod.getParameterCount();
            if (parameterCount != VALID_PARAMETER_COUNT) {
                continue;
            }

            Class<?>[] parameterTypes = packetHandlerClassDeclaredMethod.getParameterTypes();
            if (!parameterTypes[0].isAssignableFrom(RequestContext.class) || !Packet.class.isAssignableFrom(parameterTypes[1])) {
                continue;
            }

            RequestConsumer requestConsumer = assembleRequestConsumer(requestHandler, packetHandlerClassDeclaredMethod);

            Class<? extends Packet> parameterType = (Class<? extends Packet>) parameterTypes[1];
            List<RequestConsumer> consumers = requestConsumers.get(parameterType);
            if (consumers == null) {
                consumers = new CopyOnWriteArrayList<>();
                requestConsumers.put(parameterType, new CopyOnWriteArrayList<>());
            }

            consumers.add(requestConsumer);
        }

        return requestConsumers;
    }

    private RequestConsumer assembleRequestConsumer(RequestHandler requestHandler, Method method) {
        return new RequestConsumer(requestHandler, method);
    }
}
