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

    @Override
    public String readString() {
        int length = getByteBuf().readInt();
        byte[] bytes = new byte[length];
        getByteBuf().readBytes(bytes);
        return new String(bytes);
    }

    @Override
    public int readInt() {
        return getByteBuf().readInt();
    }
}
