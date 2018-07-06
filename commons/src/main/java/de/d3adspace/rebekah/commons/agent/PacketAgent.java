package de.d3adspace.rebekah.commons.agent;

import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface PacketAgent {

    /**
     * Get the packet registry.
     *
     * @return The packet registry.
     */
    PacketRegistry getPacketRegistry();

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
