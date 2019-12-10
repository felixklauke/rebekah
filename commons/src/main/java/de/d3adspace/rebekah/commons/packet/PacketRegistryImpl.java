package de.d3adspace.rebekah.commons.packet;

import com.google.common.collect.Maps;
import de.d3adspace.rebekah.commons.packet.description.MetaBasedPacketDescription;
import de.d3adspace.rebekah.commons.packet.description.PacketDescription;
import java.util.Map;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class PacketRegistryImpl implements PacketRegistry {

  /**
   * All known packet descriptions keyed by their packet ids.
   */
  private final Map<Integer, PacketDescription> packetDescriptions = Maps
    .newConcurrentMap();

  @Override
  public PacketDescription getPacketDescriptionById(int packetId) {
    return packetDescriptions.get(packetId);
  }

  @Override
  public void registerPacket(Class<? extends Packet> packetClass) {
    if (isPacketRegistered(packetClass)) {
      throw new IllegalStateException(
        "There is already a packet with the class " + packetClass);
    }

    PacketDescription packetDescription = MetaBasedPacketDescription
      .createPacketDescription(packetClass);

    if (packetDescriptions.containsKey(packetDescription.getId())) {
      throw new IllegalStateException(
        "Duplicate packet id: " + packetDescription.getId());
    }

    packetDescriptions.put(packetDescription.getId(), packetDescription);
  }

  @Override
  public void unregisterPacket(Class<? extends Packet> packetClass) {
    for (Map.Entry<Integer, PacketDescription> descriptionEntry : packetDescriptions
      .entrySet()) {
      PacketDescription value = descriptionEntry.getValue();

      if (!value.getPacketClass().isAssignableFrom(packetClass)) {
        continue;
      }

      packetDescriptions.remove(descriptionEntry.getKey());
    }
  }

  @Override
  public boolean isPacketRegistered(Class<? extends Packet> packetClass) {
    return packetDescriptions.values().stream().anyMatch(
      packetDescription -> packetDescription.getPacketClass()
        .isAssignableFrom(packetClass));
  }
}
