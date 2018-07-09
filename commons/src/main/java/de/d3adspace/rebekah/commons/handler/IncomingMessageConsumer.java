package de.d3adspace.rebekah.commons.handler;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
class IncomingMessageConsumer implements BiConsumer<MessageContext, IncomingMessage> {

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
    public void accept(MessageContext messageContext, IncomingMessage request) {
        try {
            method.invoke(incomingMessageHandler, messageContext, request);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Error handling request while executing handler method: " + method, e);
        }
    }
}
