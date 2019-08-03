package de.d3adspace.rebekah.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import de.d3adspace.rebekah.server.transport.TransportServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class RebekahServerImplTest {

  @Mock
  TransportServer transportServer;
  @Mock
  PacketRegistry packetRegistry;
  @Mock
  IncomingMessageHandlerManager incomingMessageHandlerManager;

  private Class<? extends Packet> packetClass = TestPacket.class;

  private RebekahServer rebekahServer;

  @BeforeEach
  void setUp() {
    transportServer = mock(TransportServer.class);
    rebekahServer = new RebekahServerImpl(transportServer, packetRegistry,
        incomingMessageHandlerManager);
  }

  @Test
  void testStart() {
    rebekahServer.start();

    verify(transportServer).start();
  }

  @Test
  void testStop() {
    rebekahServer.stop();

    verify(transportServer).stop();
  }

  @Test
  void testIsRunning() {
    // Given
    boolean isRunning = true;

    // When
    when(transportServer.isRunning()).thenReturn(isRunning);

    boolean rebekahServerRunning = rebekahServer.isRunning();

    // Then
    assertEquals(isRunning, rebekahServerRunning, "Server should be running.");
  }

  @Test
  void testRegisterPacket() {
    rebekahServer.registerPacket(packetClass);

    verify(packetRegistry).registerPacket(packetClass);
  }

  @Test
  void testUnregisterPacket() {
    rebekahServer.unregisterPacket(packetClass);

    verify(packetRegistry).unregisterPacket(packetClass);
  }

  @Test
  void testIsPacketRegistered() {
    boolean shouldBeRegistered = true;
    when(packetRegistry.isPacketRegistered(packetClass)).thenReturn(shouldBeRegistered);

    boolean packetRegistered = rebekahServer.isPacketRegistered(packetClass);

    verify(packetRegistry).isPacketRegistered(packetClass);
    assertEquals(shouldBeRegistered, packetRegistered, "Packet registered state differs.");
  }

  public static class TestPacket implements Packet {

    @Override
    public void encode(PacketWriter packetWriter) {

    }

    @Override
    public void decode(PacketReader packetReader) {

    }
  }
}
