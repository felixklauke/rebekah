package de.d3adspace.rebekah.commons.packet.io.binary;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

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

  @Test
  void testReadInt() {

    int integer = packetReader.readInt();

    int readInt = verify(byteBuf).readInt();

    assertEquals(integer, readInt);
  }

  @Test
  void testReadFloat() {

    float floatValue = packetReader.readFloat();

    float readFloat = verify(byteBuf).readFloat();

    assertEquals(floatValue, readFloat);
  }

  @Test
  void testReadDouble() {

    double doubleValue = packetReader.readDouble();

    double readDouble = verify(byteBuf).readDouble();

    assertEquals(doubleValue, readDouble);
  }
}
