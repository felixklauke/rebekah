package de.d3adspace.rebekah.server.netty.handler;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import de.d3adspace.rebekah.server.kernel.Kernel;
import io.reactivex.netty.channel.ObservableConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rx.Observable;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class NettyConnectionHandlerTest {

    @Mock
    Request request;
    @Mock
    Kernel kernel;
    @Mock
    ObservableConnection<Request, Response> observableConnection;

    private NettyConnectionHandler nettyConnectionHandler;

    @BeforeEach
    void setUp() {
        nettyConnectionHandler = new NettyConnectionHandler(kernel);
    }

    @Test
    void testHandle() {
        Observable<Request> requestObservable = Observable.just(request);

        when(observableConnection.getInput()).thenReturn(requestObservable);

        Observable<Void> handle = nettyConnectionHandler.handle(observableConnection);

        verify(kernel).handleRequest(Mockito.any(RequestContext.class), eq(request));

        assertNotNull(handle, "Observable of handled connection should not be null.");
    }
}