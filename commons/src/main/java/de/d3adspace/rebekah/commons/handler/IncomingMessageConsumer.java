package de.d3adspace.rebekah.commons.handler;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
class IncomingMessageConsumer implements BiConsumer<RequestContext, IncomingMessage> {

    /**
     * The corresponding packet handler.
     */
    private final IncomingMessageHandler incomingMessageHandler;

    /**
     * The method that should be executed on consume.
     */
    private final Method method;

    /**
     * Create a new packet consumer based on its underlying method.
     *
     * @param incomingMessageHandler The packet handler.
     * @param method         The method.
     */
    IncomingMessageConsumer(IncomingMessageHandler incomingMessageHandler, Method method) {
        this.incomingMessageHandler = incomingMessageHandler;
        this.method = method;
    }

    @Override
    public void accept(RequestContext requestContext, IncomingMessage request) {
        try {
            method.invoke(incomingMessageHandler, requestContext, request);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Error handling request while executing handler method: " + method, e);
        }
    }
}
