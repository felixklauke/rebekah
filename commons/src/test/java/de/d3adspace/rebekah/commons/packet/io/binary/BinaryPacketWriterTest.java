package de.d3adspace.rebekah.commons.packet.io.binary;

import io.netty.buffer.ByteBuf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class BinaryPacketWriterTest {

    @Mock
    ByteBuf byteBuf;

    private BinaryPacketWriter packetWriter;

    @BeforeEach
    void setUp() {
        packetWriter = new BinaryPacketWriter(byteBuf);
    }

    @Test
    void test() {

    }
}