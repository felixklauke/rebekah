package de.d3adspace.rebekah.commons.packet.io.binary;

import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import io.netty.buffer.ByteBuf;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class BinaryPacketWriter extends BinaryHolder implements PacketWriter {

    public BinaryPacketWriter(ByteBuf byteBuf) {
        super(byteBuf);
    }
}
