package de.d3adspace.rebekah.commons.packet.io.binary;

import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import io.netty.buffer.ByteBuf;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class BinaryPacketReader extends BinaryHolder implements PacketReader {

    public BinaryPacketReader(ByteBuf byteBuf) {
        super(byteBuf);
    }
}
