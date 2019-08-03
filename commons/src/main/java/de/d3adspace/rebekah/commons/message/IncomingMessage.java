package de.d3adspace.rebekah.commons.message;

import de.d3adspace.rebekah.commons.packet.io.PacketReader;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface IncomingMessage {

  /**
   * Read the packet content from the given reader.
   *
   * @param packetReader The reader.
   */
  void decode(PacketReader packetReader);
}
