package de.d3adspace.rebekah.commons.codec;

import com.google.common.collect.Lists;
import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.commons.packet.description.PacketDescription;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class PacketCodecTest {

    private static final int TEST_PACKET_ID = 0;

    @Mock
    PacketRegistry packetRegistry;
    @Mock
    ChannelHandlerContext channelHandlerContext;
    @Mock
    Packet packet;
    @Mock
    ByteBuf byteBuf;
    @Mock
    PacketDescription packetDescription;

    private PacketCodec packetCodec;

    @BeforeEach
    void setUp() {
        packetCodec = new PacketCodec(packetRegistry);
    }

    @Test
    void testEncode() {
        try {
            packetCodec.encode(channelHandlerContext, packet, byteBuf);

            verify(packet).encode(Mockito.any(PacketWriter.class));
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testDecode() {
        ArrayList<Object> objects = Lists.newArrayList();

        when(byteBuf.readInt()).thenReturn(TEST_PACKET_ID);
        when(packetRegistry.getPacketDescriptionById(TEST_PACKET_ID)).thenReturn(packetDescription);
        when(packetDescription.constructPacket()).thenReturn(packet);

        packetCodec.decode(channelHandlerContext, byteBuf, objects);

        verify(packet).decode(Mockito.any(PacketReader.class));
        assertTrue(objects.contains(packet), "Packet should be added to outgoing objects.");
    }
}