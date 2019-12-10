package de.d3adspace.rebekah.commons.packet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.d3adspace.rebekah.commons.packet.description.PacketDescription;
import de.d3adspace.rebekah.commons.packet.description.annotation.PacketMeta;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
class PacketRegistryImplTest {

  private static final int TEST_PACKET_ID = 0;
  private static final Class<? extends Packet> TEST_PACKET_CLASS = TestPacket.class;
  private static final Class<? extends Packet> TEST_PACKET_STACK_CLASS = TestPacketStack.class;
  private static final Class<? extends Packet> TEST_PACKET_CLASS_DUPLICATE_ID = TestPacketDuplicateId.class;

  private PacketRegistry packetRegistry;

  @BeforeEach
  void setUp() {
    packetRegistry = new PacketRegistryImpl();
    packetRegistry.registerPacket(TEST_PACKET_CLASS);
    packetRegistry.registerPacket(TEST_PACKET_STACK_CLASS);
  }

  @Test
  void testGetPacketDescriptionById() {
    PacketDescription packetDescriptionById = packetRegistry
      .getPacketDescriptionById(TEST_PACKET_ID);

    assertNotNull(packetDescriptionById, "Description should not be null");
    assertEquals(TEST_PACKET_ID, packetDescriptionById.getId(),
      "Packet id differs");
    assertEquals(TEST_PACKET_CLASS, packetDescriptionById.getPacketClass(),
      "Packet class differs.");
  }

  @Test
  void testRegisterPacket() {
  }

  @Test
  void testRegisterPacketTwice() {
    Executable executable = () -> packetRegistry
      .registerPacket(TEST_PACKET_CLASS);

    assertThrows(IllegalStateException.class, executable);
  }

  @Test
  void testRegisterPacketDuplicateId() {
    Executable executable = () -> packetRegistry
      .registerPacket(TEST_PACKET_CLASS_DUPLICATE_ID);

    assertThrows(IllegalStateException.class, executable);
  }

  @Test
  void testUnregisterPacket() {
    packetRegistry.unregisterPacket(TEST_PACKET_CLASS);

    assertFalse(packetRegistry.isPacketRegistered(TEST_PACKET_CLASS),
      "Packet shouldn't be registered");
  }

  @Test
  void testIsPacketRegistered() {
    assertTrue(packetRegistry.isPacketRegistered(TEST_PACKET_CLASS),
      "Packet should be registered.");
    assertFalse(
      packetRegistry.isPacketRegistered(TEST_PACKET_CLASS_DUPLICATE_ID),
      "Packet should not be registered.");
  }

  @PacketMeta(id = TEST_PACKET_ID)
  public static class TestPacket implements Packet {

    public TestPacket() {

    }

    @Override
    public void encode(PacketWriter packetWriter) {

    }

    @Override
    public void decode(PacketReader packetReader) {

    }
  }

  @PacketMeta(id = TEST_PACKET_ID + 1)
  private static class TestPacketStack implements Packet {

    public TestPacketStack() {

    }

    @Override
    public void encode(PacketWriter packetWriter) {

    }

    @Override
    public void decode(PacketReader packetReader) {

    }
  }

  @PacketMeta(id = TEST_PACKET_ID)
  private static class TestPacketDuplicateId implements Packet {

    public TestPacketDuplicateId() {

    }

    @Override
    public void encode(PacketWriter packetWriter) {

    }

    @Override
    public void decode(PacketReader packetReader) {

    }
  }
}
