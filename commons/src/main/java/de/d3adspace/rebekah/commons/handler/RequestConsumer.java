package de.d3adspace.rebekah.commons.handler;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.request.Request;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
class RequestConsumer implements BiConsumer<RequestContext, Request> {

    /**
     * The corresponding packet handler.
     */
    private final RequestHandler requestHandler;

    /**
     * The method that should be executed on consume.
     */
    private final Method method;

    /**
     * Create a new packet consumer based on its underlying method.
     *
     * @param requestHandler The packet handler.
     * @param method         The method.
     */
    RequestConsumer(RequestHandler requestHandler, Method method) {
        this.requestHandler = requestHandler;
        this.method = method;
    }

    @Override
    public void accept(RequestContext requestContext, Request request) {
        try {
            method.invoke(requestHandler, requestContext, request);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Error handling request while executing handler method: " + method, e);
        }
    }
}
