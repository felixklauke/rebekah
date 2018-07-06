package de.d3adspace.rebekah.server;

import de.d3adspace.rebekah.commons.handler.RequestHandler;
import de.d3adspace.rebekah.commons.packet.Packet;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface RebekahServer {

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
     * Check if the given request handler is already registered.
     *
     * @param requestHandler The request handler.
     *
     * @return If the given request handler is already registered.
     */
    boolean isRequestHandlerRegistered(RequestHandler requestHandler);

    /**
     * Register the packet represented by the given class.
     *
     * @param packetClass The class that represents the packet.
     */
    void registerPacket(Class<? extends Packet> packetClass);

    /**
     * Unregister the packet represented by the given class.
     *
     * @param packetClass The class that represents the packet.
     */
    void unregisterPacket(Class<? extends Packet> packetClass);

    /**
     * Check if there is a packet registered that would be represented by the given class.
     *
     * @param packetClass The class that would represent the packet.
     *
     * @return If the packet is registered.
     */
    boolean isPacketRegistered(Class<? extends Packet> packetClass);
}
