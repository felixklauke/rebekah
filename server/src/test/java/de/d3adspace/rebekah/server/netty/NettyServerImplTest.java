package de.d3adspace.rebekah.server.netty;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.server.RxServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class NettyServerImplTest {

    @Mock
    private RxServer<Request, Response> rxServer;

    private NettyServerImpl nettyServer;

    @BeforeEach
    void setUp() {
        nettyServer = new NettyServerImpl(rxServer);
    }

    @Test
    void testStart() {
        nettyServer.start();

        verify(rxServer).start();
    }

    @Test
    void testStop() throws InterruptedException {
        nettyServer.stop();

        verify(rxServer).shutdown();
    }

    @Test
    void testStopWithInterrupt() {
        // Given
        InterruptedException test = new InterruptedException("Test");

        // When
        try {
            doThrow(test).when(rxServer).shutdown();
        } catch (InterruptedException e) {
            fail();
        }

        nettyServer.stop();
    }

    @Test
    void testIsRunnijg() {
        boolean running = nettyServer.isRunning();

        assertFalse(running, "Server shouldn't have been running.");

        nettyServer.start();

        running = nettyServer.isRunning();

        assertTrue(running, "Server should have been running.");
    }
}