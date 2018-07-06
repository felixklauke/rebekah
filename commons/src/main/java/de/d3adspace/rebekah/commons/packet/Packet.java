package de.d3adspace.rebekah.commons.packet;

import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface Packet extends Request, Response {

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
