package de.d3adspace.rebekah.client;

import de.d3adspace.rebekah.client.transport.TransportClient;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class RebekahClientImplTest {

    @Mock
    TransportClient transportClient;
    @Mock
    PacketRegistry packetRegistry;
    @Mock
    IncomingMessageHandlerManager incomingMessageHandlerManager;
    @Mock
    OutgoingMessage outgoingMessage;

    private Class<? extends Packet> packetClass = TestPacket.class;

    private RebekahClient rebekahClient;

    @BeforeEach
    void setUp() {
        rebekahClient = new RebekahClientImpl(packetRegistry, transportClient, incomingMessageHandlerManager);
    }

    @Test
    void testConnect() {
        rebekahClient.connect();

        verify(transportClient).connect();
    }

    @Test
    void testIsConnected() {
        boolean shouldBeConnected = true;
        when(transportClient.isConnected()).thenReturn(shouldBeConnected);

        boolean connected = rebekahClient.isConnected();

        verify(transportClient).isConnected();

        assertEquals(shouldBeConnected, connected, "Connected state differs.");
    }

    @Test
    void testDisconnect() {
        rebekahClient.disconnect();

        verify(transportClient).disconnect();
    }


    @Test
    void testRegisterPacket() {
        rebekahClient.registerPacket(packetClass);

        verify(packetRegistry).registerPacket(packetClass);
    }

    @Test
    void testUnregisterPacket() {
        rebekahClient.unregisterPacket(packetClass);

        verify(packetRegistry).unregisterPacket(packetClass);
    }

    @Test
    void testIsPacketRegistered() {
        boolean shouldBeRegistered = true;

        when(this.packetRegistry.isPacketRegistered(packetClass)).thenReturn(shouldBeRegistered);

        boolean packetRegistered = rebekahClient.isPacketRegistered(packetClass);

        verify(this.packetRegistry).isPacketRegistered(packetClass);
        assertEquals(shouldBeRegistered, packetRegistered, "Packet registered state differs :(");
    }

    @Test
    void testSendMessage() {
        rebekahClient.sendMessage(outgoingMessage);

        verify(transportClient).sendRequest(outgoingMessage);
    }

    public static class TestPacket implements Packet {

        @Override
        public void encode(PacketWriter packetWriter) {

        }

        @Override
        public void decode(PacketReader packetReader) {

        }
    }
}