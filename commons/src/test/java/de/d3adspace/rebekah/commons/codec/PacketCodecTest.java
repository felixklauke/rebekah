package de.d3adspace.rebekah.commons.codec;

import com.google.common.collect.Lists;
import de.d3adspace.rebekah.commons.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class PacketCodecTest {

    @Mock
    ChannelHandlerContext channelHandlerContext;
    @Mock
    Packet packet;
    @Mock
    ByteBuf byteBuf;

    private PacketCodec packetCodec;

    @BeforeEach
    void setUp() {
        packetCodec = new PacketCodec();
    }

    @Test
    void testEncode() {
        try {
            packetCodec.encode(channelHandlerContext, packet, byteBuf);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testDecode() {
        ArrayList<Object> objects = Lists.newArrayList();

        try {
            packetCodec.decode(channelHandlerContext, byteBuf, objects);
        } catch (Exception e) {
            fail(e);
        }
    }
}