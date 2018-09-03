package de.d3adspace.rebekah.commons.packet.io.binary;

import com.google.common.base.Charsets;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import io.netty.buffer.ByteBuf;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class BinaryPacketWriter extends BinaryHolder implements PacketWriter {

    public BinaryPacketWriter(ByteBuf byteBuf) {
        super(byteBuf);
    }

    @Override
    public void writeString(String string) {

        byte[] bytes = string.getBytes(Charsets.UTF_8);

        // Write string length
        getByteBuf().writeInt(bytes.length);

        // Write content
        getByteBuf().writeBytes(bytes);
    }

    @Override
    public void writeInt(int integer) {

        getByteBuf().writeInt(integer);
    }

    @Override
    public void writeFloat(float floatValue) {

        getByteBuf().writeFloat(floatValue);
    }

    @Override
    public void writeDouble(double doubleValue) {

        getByteBuf().writeDouble(doubleValue);
    }
}
