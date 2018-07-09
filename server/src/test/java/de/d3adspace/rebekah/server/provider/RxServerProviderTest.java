package de.d3adspace.rebekah.server.provider;

import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import io.reactivex.netty.channel.ConnectionHandler;
import io.reactivex.netty.pipeline.PipelineConfigurator;
import io.reactivex.netty.server.RxServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class RxServerProviderTest {

    private static final int TEST_PORT = 1000;
    @Mock
    ConnectionHandler<IncomingMessage, OutgoingMessage> connectionHandler;
    @Mock
    PipelineConfigurator<IncomingMessage, OutgoingMessage> pipelineConfigurator;

    private RxServerProvider rxServerProvider;

    @BeforeEach
    void setUp() {
        rxServerProvider = new RxServerProvider(TEST_PORT, pipelineConfigurator, connectionHandler);
    }

    @Test
    void testGet() {
        RxServer<IncomingMessage, OutgoingMessage> rxServer = rxServerProvider.get();

        assertNotNull(rxServer, "Server should not be null.");
        assertEquals(TEST_PORT, rxServer.getServerPort());
    }
}