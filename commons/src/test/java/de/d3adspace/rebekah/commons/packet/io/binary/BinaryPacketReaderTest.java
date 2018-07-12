package de.d3adspace.rebekah.commons.packet.io.binary;

import io.netty.buffer.ByteBuf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class BinaryPacketReaderTest {

    @Mock
    ByteBuf byteBuf;

    private BinaryPacketReader packetReader;

    @BeforeEach
    void setUp() {
        packetReader = new BinaryPacketReader(byteBuf);
    }

    @Test
    void testReadString() {
        String readString = packetReader.readString();

        int readInt = verify(byteBuf).readInt();
        byte[] bytes = new byte[readInt];
        verify(byteBuf).readBytes(eq(bytes));

        assertArrayEquals(readString.getBytes(), bytes);
    }
}