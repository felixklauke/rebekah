package de.d3adspace.rebekah.commons.packet.description;

import de.d3adspace.rebekah.commons.packet.Packet;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface PacketDescription {

    /**
     * Get the unique id of the packet.
     *
     * @return The packet id.
     */
    int getId();

    Class<? extends Packet> getPacketClass();

    /**
     * Construct an empty instance of the packet.
     *
     * @return The constructed packet.
     */
    Packet constructPacket();
}
