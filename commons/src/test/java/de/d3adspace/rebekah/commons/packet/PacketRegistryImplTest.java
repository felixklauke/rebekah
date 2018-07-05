package de.d3adspace.rebekah.commons.packet;

import de.d3adspace.rebekah.commons.packet.description.PacketDescription;
import de.d3adspace.rebekah.commons.packet.description.annotation.PacketMeta;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
class PacketRegistryImplTest {

    private static final int TEST_PACKET_ID = 0;
    private static final Class<? extends Packet> TEST_PACKET_CLASS = TestPacket.class;
    private static final Class<? extends Packet> TEST_PACKET_CLASS_DUPLICATE_ID = TestPacketDuplicateId.class;

    private PacketRegistry packetRegistry;

    @BeforeEach
    void setUp() {
        packetRegistry = new PacketRegistryImpl();
        packetRegistry.registerPacket(TEST_PACKET_CLASS);
    }

    @Test
    void testGetPacketDescriptionById() {
        PacketDescription packetDescriptionById = packetRegistry.getPacketDescriptionById(TEST_PACKET_ID);

        assertNotNull(packetDescriptionById, "Description should not be null");
        assertEquals(TEST_PACKET_ID, packetDescriptionById.getId(), "Packet id differs");
        assertEquals(TEST_PACKET_CLASS, packetDescriptionById.getPacketClass(), "Packet class differs.");
    }

    @Test
    void testRegisterPacket() {
    }

    @Test
    void testRegisterPacketTwice() {
        Executable executable = () -> packetRegistry.registerPacket(TEST_PACKET_CLASS);

        assertThrows(IllegalStateException.class, executable);
    }

    @Test
    void testRegisterPacketDuplicateId() {
        Executable executable = () -> packetRegistry.registerPacket(TEST_PACKET_CLASS_DUPLICATE_ID);

        assertThrows(IllegalStateException.class, executable);
    }

    @Test
    void testUnregisterPacket() {
        packetRegistry.unregisterPacket(TEST_PACKET_CLASS);

        assertFalse(packetRegistry.isPacketRegistered(TEST_PACKET_CLASS), "Packet shouldn't be registered");
    }

    @Test
    void testIsPacketRegistered() {
        assertTrue(packetRegistry.isPacketRegistered(TEST_PACKET_CLASS), "Packet should be registered.");
    }

    @PacketMeta(id = TEST_PACKET_ID)
    private class TestPacket implements Packet {

        @Override
        public void encode(PacketWriter packetWriter) {

        }

        @Override
        public void decode(PacketReader packetReader) {

        }
    }

    @PacketMeta(id = TEST_PACKET_ID)
    private class TestPacketDuplicateId implements Packet {

        @Override
        public void encode(PacketWriter packetWriter) {

        }

        @Override
        public void decode(PacketReader packetReader) {

        }
    }
}