package de.d3adspace.rebekah.commons.codec;

import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.commons.packet.description.PacketDescription;
import de.d3adspace.rebekah.commons.packet.factory.PacketFactory;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import de.d3adspace.rebekah.commons.packet.io.binary.BinaryPacketReader;
import de.d3adspace.rebekah.commons.packet.io.binary.BinaryPacketWriter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class PacketCodec extends ByteToMessageCodec<Packet> {

  /**
   * The registry that manages the references to all known packets.
   */
  private final PacketRegistry packetRegistry;

  /**
   * The factory used for packet creation.
   */
  private final PacketFactory packetFactory;

  /**
   * Create a new codec by its registry.
   *
   * @param packetRegistry The registry.
   * @param packetFactory  The packet factory.
   */
  @Inject
  public PacketCodec(
    PacketRegistry packetRegistry,
    PacketFactory packetFactory
  ) {
    this.packetRegistry = packetRegistry;
    this.packetFactory = packetFactory;
  }

  @Override
  protected void encode(
    ChannelHandlerContext ctx,
    Packet packet,
    ByteBuf outgoingByteBuf
  ) {
    PacketWriter packetWriter = new BinaryPacketWriter(outgoingByteBuf);
    packet.encode(packetWriter);
  }

  @Override
  protected void decode(
    ChannelHandlerContext ctx,
    ByteBuf incomingByteBuf,
    List<Object> outgoingObjects
  ) {
    int packetId = incomingByteBuf.readInt();

    PacketDescription packetDescription = packetRegistry
      .getPacketDescriptionById(packetId);

    if (packetDescription == null) {
      throw new IllegalStateException("Unknown packet id: " + packetId);
    }

    Class<? extends Packet> packetClass = packetDescription.getPacketClass();
    Packet packet = packetFactory.createPacket(packetClass);
    PacketReader packetReader = new BinaryPacketReader(incomingByteBuf);
    packet.decode(packetReader);

    outgoingObjects.add(packet);
  }
}
