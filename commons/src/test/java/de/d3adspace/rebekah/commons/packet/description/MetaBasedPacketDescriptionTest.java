package de.d3adspace.rebekah.commons.packet.description;

import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.description.annotation.PacketMeta;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class MetaBasedPacketDescriptionTest {

    private static final Class<? extends Packet> TEST_PACKET_CLASS = TestPacket.class;
    private static final int TEST_PACKET_ID = 0;
    private static final Class<? extends Packet> TEST_PACKET_CLASS_WITHOUT_META = TestPacketWithOutMeta.class;
    private static final Class<? extends Packet> TEST_PACKET_CLASS_INVALID_CONSTRUCTOR = TestPacketWithInvalidConstructor.class;

    @Mock
    PacketMeta packetMeta;
    private MetaBasedPacketDescription metaBasedPacketDescription;

    @BeforeEach
    void setUp() {

        metaBasedPacketDescription = new MetaBasedPacketDescription(packetMeta, TEST_PACKET_CLASS);
    }

    @Test
    void testCreatePacketDescription() {
        when(packetMeta.id()).thenReturn(TEST_PACKET_ID);

        PacketDescription packetDescription = MetaBasedPacketDescription.createPacketDescription(TEST_PACKET_CLASS);

        assertEquals(metaBasedPacketDescription.getId(), packetDescription.getId());
        assertEquals(metaBasedPacketDescription.getPacketClass(), packetDescription.getPacketClass());
    }

    @Test
    void testCreatePacketDescriptionWithInvalidClass() {
        Executable executable = () -> MetaBasedPacketDescription.createPacketDescription(TEST_PACKET_CLASS_WITHOUT_META);
        assertThrows(IllegalStateException.class, executable);
    }

    @Test
    void testCreatePacketDescriptionWithInvalidConstructor() {
        Executable executable = () -> MetaBasedPacketDescription.createPacketDescription(TEST_PACKET_CLASS_INVALID_CONSTRUCTOR);
        assertThrows(IllegalStateException.class, executable);
    }

    @Test
    void testGetId() {
        when(packetMeta.id()).thenReturn(TEST_PACKET_ID);

        assertEquals(TEST_PACKET_ID, metaBasedPacketDescription.getId());
    }

    @Test
    void testGetPacketClass() {
        assertEquals(TEST_PACKET_CLASS, metaBasedPacketDescription.getPacketClass());
    }

    @PacketMeta(id = TEST_PACKET_ID)
    public static class TestPacket implements Packet {

        @Override
        public void encode(PacketWriter packetWriter) {

        }

        @Override
        public void decode(PacketReader packetReader) {

        }
    }

    public static class TestPacketWithOutMeta implements Packet {

        @Override
        public void encode(PacketWriter packetWriter) {

        }

        @Override
        public void decode(PacketReader packetReader) {

        }
    }

    @PacketMeta(id = TEST_PACKET_ID + 1)
    public static class TestPacketWithInvalidConstructor implements Packet {

        private final int test;

        private TestPacketWithInvalidConstructor(int test) {
            this.test = test;
        }

        @Override
        public void encode(PacketWriter packetWriter) {

        }

        @Override
        public void decode(PacketReader packetReader) {

        }
    }
}