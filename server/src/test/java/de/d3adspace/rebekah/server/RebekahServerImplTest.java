package de.d3adspace.rebekah.server;

import de.d3adspace.rebekah.commons.handler.RequestHandler;
import de.d3adspace.rebekah.commons.handler.RequestHandlerManager;
import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.server.transport.TransportServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class RebekahServerImplTest {

    @Mock
    TransportServer transportServer;
    @Mock
    PacketRegistry packetRegistry;
    @Mock
    RequestHandlerManager requestHandlerManager;
    @Mock
    RequestHandler requestHandler;
    @Mock
    Class<? extends Packet> packetClass;

    private RebekahServer rebekahServer;

    @BeforeEach
    void setUp() {
        transportServer = mock(TransportServer.class);
        rebekahServer = new RebekahServerImpl(transportServer, packetRegistry, requestHandlerManager);
    }

    @Test
    void testStart() {
        rebekahServer.start();

        verify(transportServer).start();
    }

    @Test
    void testStop() {
        rebekahServer.stop();

        verify(transportServer).stop();
    }

    @Test
    void testIsRunning() {
        // Given
        boolean isRunning = true;

        // When
        when(transportServer.isRunning()).thenReturn(isRunning);

        boolean rebekahServerRunning = rebekahServer.isRunning();

        // Then
        assertEquals(isRunning, rebekahServerRunning, "Server should be running.");
    }

    @Test
    void testRegisterRequestHandler() {
        rebekahServer.registerRequestHandler(requestHandler);

        verify(requestHandlerManager).registerRequestHandler(requestHandler);
    }

    @Test
    void testUnregisterRequestHandler() {
        rebekahServer.unregisterRequestHandler(requestHandler);

        verify(requestHandlerManager).unregisterRequestHandler(requestHandler);
    }

    @Test
    void testRegisterPacket() {
        rebekahServer.registerPacket(packetClass);

        verify(packetRegistry).registerPacket(packetClass);
    }

    @Test
    void testUnregisterPacket() {
        rebekahServer.unregisterPacket(packetClass);

        verify(packetRegistry).unregisterPacket(packetClass);
    }

    @Test
    void testIsPacketRegistered() {
        boolean shouldBeRegistered = true;
        when(packetRegistry.isPacketRegistered(packetClass)).thenReturn(shouldBeRegistered);

        boolean packetRegistered = rebekahServer.isPacketRegistered(packetClass);

        verify(packetRegistry).isPacketRegistered(packetClass);
        assertEquals(shouldBeRegistered, packetRegistered, "Packet registered state differs.");
    }
}