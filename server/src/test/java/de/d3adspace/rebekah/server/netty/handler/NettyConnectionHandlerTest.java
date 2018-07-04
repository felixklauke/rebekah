package de.d3adspace.rebekah.server.netty.handler;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.channel.ObservableConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rx.Observable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class NettyConnectionHandlerTest {

    @Mock
    ObservableConnection<Request, Response> observableConnection;

    private NettyConnectionHandler nettyConnectionHandler;

    @BeforeEach
    void setUp() {
        nettyConnectionHandler = new NettyConnectionHandler();
    }

    @Test
    void testHandle() {
        Observable<Void> handle = nettyConnectionHandler.handle(observableConnection);

        assertNotNull(handle, "Observable of handled connection should not be null.");
    }
}