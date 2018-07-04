package de.d3adspace.rebekah.server;

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

    private RebekahServer rebekahServer;

    @BeforeEach
    void setUp() {
        transportServer = mock(TransportServer.class);
        rebekahServer = new RebekahServerImpl(transportServer);
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
}