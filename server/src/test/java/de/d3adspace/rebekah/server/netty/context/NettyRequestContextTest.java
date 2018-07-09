package de.d3adspace.rebekah.server.netty.context;

import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import io.reactivex.netty.channel.ObservableConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class NettyRequestContextTest {

    @Mock
    private IncomingMessage request;
    @Mock
    private ObservableConnection<IncomingMessage, OutgoingMessage> observableConnection;
    @Mock
    private OutgoingMessage response;

    private NettyRequestContext nettyRequestContext;

    @BeforeEach
    void setUp() {
        nettyRequestContext = new NettyRequestContext(observableConnection, request);
    }

    @Test
    void testResume() {
        nettyRequestContext.resume(response);

        verify(observableConnection).writeAndFlush(response);
    }
}