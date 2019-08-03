package de.d3adspace.rebekah.commons.packet.io.binary;

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
class BinaryPacketWriterTest {

  @Mock
  ByteBuf byteBuf;

  private BinaryPacketWriter packetWriter;

  @BeforeEach
  void setUp() {

    packetWriter = new BinaryPacketWriter(byteBuf);
  }

  @Test
  void testWriteString() {

    String testString = "afwafagfwawwfgagawga";

    packetWriter.writeString(testString);

    verify(byteBuf).writeInt(testString.getBytes().length);
    verify(byteBuf).writeBytes(testString.getBytes());
  }

  @Test
  void testWriteInt() {

    int testInteger = 10;

    packetWriter.writeInt(testInteger);

    verify(byteBuf).writeInt(testInteger);
  }

  @Test
  void testWriteFloat() {

    float testFloat = 10.1F;

    packetWriter.writeFloat(testFloat);

    verify(byteBuf).writeFloat(testFloat);
  }

  @Test
  void testWriteDouble() {

    double testDouble = 10.2D;

    packetWriter.writeDouble(testDouble);

    verify(byteBuf).writeDouble(testDouble);
  }
}
