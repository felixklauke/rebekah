package de.d3adspace.rebekah.commons.message;

import de.d3adspace.rebekah.commons.packet.io.PacketWriter;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface OutgoingMessage {

    /**
     * Write the packet using the given writer.
     *
     * @param packetWriter The packet writer.
     */
    void encode(PacketWriter packetWriter);
}
