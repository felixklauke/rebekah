package de.d3adspace.rebekah.commons.agent;

import de.d3adspace.rebekah.commons.handler.IncomingMessageHandler;
import de.d3adspace.rebekah.commons.packet.Packet;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface PacketAgent {

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

    /**
     * Register the given message handler.
     *
     * @param incomingMessageHandler The message handler.
     */
    void registerMessageHandler(IncomingMessageHandler incomingMessageHandler);

    /**
     * Unregister the given message handler.
     *
     * @param incomingMessageHandler The message handler.
     */
    void unregisterMessageHandler(IncomingMessageHandler incomingMessageHandler);

    /**
     * Check if the given message handler is already registered.
     *
     * @param incomingMessageHandler The message handler.
     *
     * @return If the given message handler is already registered.
     */
    boolean isMessageHandlerRegistered(IncomingMessageHandler incomingMessageHandler);
}
