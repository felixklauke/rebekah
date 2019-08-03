package de.d3adspace.rebekah.commons.packet;

import de.d3adspace.rebekah.commons.packet.description.PacketDescription;

/**
 * Manages all known packets.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface PacketRegistry {

  /**
   * Get the description of a packet by its id.
   *
   * @param packetId The id of the packet.
   * @return The packet description.
   */
  PacketDescription getPacketDescriptionById(int packetId);

  /**
   * Register the packet represented by the given class.
   *
   * @param packetClass The class that represents the packet.
   */
  void registerPacket(Class<? extends Packet> packetClass);

  /**
   * Unregister the packet represented by the given class.
   *
   * @param packetClass The class that represents the packet.
   */
  void unregisterPacket(Class<? extends Packet> packetClass);

  /**
   * Check if there is a packet registered that would be represented by the given class.
   *
   * @param packetClass The class that would represent the packet.
   * @return If the packet is registered.
   */
  boolean isPacketRegistered(Class<? extends Packet> packetClass);
}
