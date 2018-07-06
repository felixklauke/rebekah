package de.d3adspace.rebekah.commons.handler;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.request.Request;

/**
 * Manages all known packet handlers.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface RequestHandlerManager {

    /**
     * Register the given request handler.
     *
     * @param requestHandler The packet handler.
     */
    void registerRequestHandler(RequestHandler requestHandler);

    /**
     * Unregister the given request handler.
     *
     * @param requestHandler The request handler.
     */
    void unregisterRequestHandler(RequestHandler requestHandler);

    /**
     * Process the given request in the given request by let it be handled by all eligible handlers.
     *
     * @param requestContext The context of the request.
     * @param request        The request.
     */
    void process(RequestContext requestContext, Request request);
}
