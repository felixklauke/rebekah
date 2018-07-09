package de.d3adspace.rebekah.server;

import de.d3adspace.rebekah.commons.agent.PacketAgent;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandler;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface RebekahServer extends PacketAgent {

    /**
     * Start the server.
     */
    void start();

    /**
     * Stop the server.
     */
    void stop();

    /**
     * Check if the server is running.
     *
     * @return If the server is running.
     */
    boolean isRunning();

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
}
