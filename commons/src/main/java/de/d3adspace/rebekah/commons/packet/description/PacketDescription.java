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

    /**
     * Get the class of the packet.
     *
     * @return The class of the packet.
     */
    Class<? extends Packet> getPacketClass();
}
