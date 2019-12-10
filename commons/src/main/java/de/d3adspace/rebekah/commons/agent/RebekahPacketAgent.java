package de.d3adspace.rebekah.commons.agent;

import de.d3adspace.rebekah.commons.handler.IncomingMessageHandler;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahPacketAgent implements PacketAgent {

  /**
   * The manager of all request managers.
   */
  protected final IncomingMessageHandlerManager incomingMessageHandlerManager;
  /**
   * The registry of all known packets.
   */
  private final PacketRegistry packetRegistry;

  /**
   * Create a new packet agent by its underlying registry.
   *
   * @param packetRegistry The packet registry.
   */
  public RebekahPacketAgent(PacketRegistry packetRegistry,
    IncomingMessageHandlerManager incomingMessageHandlerManager) {
    this.packetRegistry = packetRegistry;
    this.incomingMessageHandlerManager = incomingMessageHandlerManager;
  }

  protected PacketRegistry getPacketRegistry() {
    return packetRegistry;
  }

  @Override
  public void registerPacket(Class<? extends Packet> packetClass) {
    packetRegistry.registerPacket(packetClass);
  }

  @Override
  public void unregisterPacket(Class<? extends Packet> packetClass) {
    packetRegistry.unregisterPacket(packetClass);
  }

  @Override
  public boolean isPacketRegistered(Class<? extends Packet> packetClass) {
    return packetRegistry.isPacketRegistered(packetClass);
  }

  @Override
  public void registerMessageHandler(
    IncomingMessageHandler incomingMessageHandler) {
    incomingMessageHandlerManager
      .registerMessageHandler(incomingMessageHandler);
  }

  @Override
  public void unregisterMessageHandler(
    IncomingMessageHandler incomingMessageHandler) {
    incomingMessageHandlerManager
      .unregisterMessageHandler(incomingMessageHandler);
  }

  @Override
  public boolean isMessageHandlerRegistered(
    IncomingMessageHandler incomingMessageHandler) {
    return incomingMessageHandlerManager
      .isMessageHandlerRegistered(incomingMessageHandler);
  }
}
