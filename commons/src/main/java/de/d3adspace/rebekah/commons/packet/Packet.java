package de.d3adspace.rebekah.commons.packet;

import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface Packet {

    /**
     * Write the packet using the given writer.
     *
     * @param packetWriter The packet writer.
     */
    void encode(PacketWriter packetWriter);

    /**
     * Read the packet content from the given reader.
     *
     * @param packetReader The reader.
     */
    void decode(PacketReader packetReader);
}