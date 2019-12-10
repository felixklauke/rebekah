package de.d3adspace.rebekah.commons.packet.factory;

import com.google.inject.Injector;
import de.d3adspace.rebekah.commons.packet.Packet;
import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class GuicePacketFactory implements PacketFactory {

  /**
   * The guice injector needed for packet instantiation.
   */
  private final Injector injector;

  @Inject
  public GuicePacketFactory(Injector injector) {
    this.injector = injector;
  }

  @Override
  public Packet createPacket(Class<? extends Packet> packetClass) {
    return injector.getInstance(packetClass);
  }
}
