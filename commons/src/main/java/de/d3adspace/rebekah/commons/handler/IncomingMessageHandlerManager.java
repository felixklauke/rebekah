package de.d3adspace.rebekah.commons.handler;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;

/**
 * Manages all known packet handlers.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface IncomingMessageHandlerManager {

    /**
     * Register the given request handler.
     *
     * @param incomingMessageHandler The packet handler.
     */
    void registerRequestHandler(IncomingMessageHandler incomingMessageHandler);

    /**
     * Unregister the given request handler.
     *
     * @param incomingMessageHandler The request handler.
     */
    void unregisterRequestHandler(IncomingMessageHandler incomingMessageHandler);

    /**
     * Check if the given request handler is already registered.
     *
     * @param incomingMessageHandler The request handler.
     *
     * @return If the given request handler is already registered.
     */
    boolean isRequestHandlerRegistered(IncomingMessageHandler incomingMessageHandler);

    /**
     * Process the given request in the given request by let it be handled by all eligible handlers.
     *
     * @param messageContext The context of the request.
     * @param request        The request.
     */
    void process(MessageContext messageContext, IncomingMessage request);
}
