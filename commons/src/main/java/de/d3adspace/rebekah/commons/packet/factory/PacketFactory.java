package de.d3adspace.rebekah.commons.packet.factory;

import de.d3adspace.rebekah.commons.packet.Packet;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface PacketFactory {

    /**
     * Create a packet instance by its class.
     *
     * @param packetClass The class of the packet.
     *
     * @return The packet instance.
     */
    Packet createPacket(Class<? extends Packet> packetClass);
}
