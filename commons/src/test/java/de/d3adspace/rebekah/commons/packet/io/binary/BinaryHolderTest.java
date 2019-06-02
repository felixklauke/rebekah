package de.d3adspace.rebekah.commons.packet.io.binary;

import io.netty.buffer.ByteBuf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class BinaryHolderTest {

    @Mock
    private ByteBuf byteBuf;

    private BinaryHolder binaryHolder;

    @BeforeEach
    void setUp() {
        binaryHolder = new BinaryHolder(byteBuf);
    }

    @Test
    void testGetByteBuf() {

        assertEquals(byteBuf, binaryHolder.getByteBuf());
    }
}