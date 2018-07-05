package de.d3adspace.rebekah.commons.codec;

import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.commons.packet.description.PacketDescription;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import de.d3adspace.rebekah.commons.packet.io.binary.BinaryPacketReader;
import de.d3adspace.rebekah.commons.packet.io.binary.BinaryPacketWriter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class PacketCodec extends ByteToMessageCodec<Packet> {

    /**
     * The registry that manages the references to all known packets.
     */
    private final PacketRegistry packetRegistry;

    /**
     * Create a new codec by its registry.
     *
     * @param packetRegistry The registry.
     */
    @Inject
    public PacketCodec(PacketRegistry packetRegistry) {
        this.packetRegistry = packetRegistry;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf outgoingByteBuf) {
        PacketWriter packetWriter = new BinaryPacketWriter(outgoingByteBuf);
        packet.encode(packetWriter);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf incomingByteBuf, List<Object> outgoingObjects) {
        int packetId = incomingByteBuf.readInt();

        PacketDescription packetDescription = packetRegistry.getPacketDescriptionById(packetId);

        if (packetDescription == null) {
            throw new IllegalStateException("Unknown packet id: " + packetId);
        }

        Packet packet = packetDescription.constructPacket();
        PacketReader packetReader = new BinaryPacketReader(incomingByteBuf);
        packet.decode(packetReader);

        outgoingObjects.add(packet);
    }
}
