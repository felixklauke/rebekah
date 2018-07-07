package de.d3adspace.rebekah.client.netty;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.channel.ObservableConnection;
import io.reactivex.netty.client.RxClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rx.subjects.BehaviorSubject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class NettyClientTest {

    @Mock
    ObservableConnection<Response, Request> observableConnection;
    @Mock
    Request request;
    @Mock
    RxClient<Request, Response> rxClient;
    private BehaviorSubject<ObservableConnection<Response, Request>> observableConnectionObservable = BehaviorSubject.create();

    private NettyClient nettyClient;

    @BeforeEach
    void setUp() {
        nettyClient = new NettyClient(rxClient);
    }

    @Test
    void testConnect() {
        when(rxClient.connect()).thenReturn(observableConnectionObservable);
        nettyClient.connect();

        verify(rxClient).connect();
    }

    @Test
    void testIsConnected() {
        boolean clientConnected = nettyClient.isConnected();

        assertFalse(clientConnected, "Client should not be connected.");
    }

    @Test
    void testAfterConnectPending() {
        when(rxClient.connect()).thenReturn(observableConnectionObservable);

        nettyClient.connect();

        boolean clientConnected = nettyClient.isConnected();

        assertFalse(clientConnected, "Client should not be connected.");
    }

    @Test
    void testAfterConnectFinished() {
        when(rxClient.connect()).thenReturn(observableConnectionObservable);

        nettyClient.connect();


        observableConnectionObservable.onNext(observableConnection);

        boolean clientConnected = nettyClient.isConnected();

        assertTrue(clientConnected, "Client should be connected.");
    }

    @Test
    void testDisconnect() {
        nettyClient.disconnect();
    }

    @Test
    void testSendRequest() {
        when(rxClient.connect()).thenReturn(observableConnectionObservable);

        nettyClient.connect();

        observableConnectionObservable.onNext(observableConnection);

        nettyClient.sendRequest(request);

        verify(observableConnection).writeAndFlush(request);
    }

    @Test
    void testSingleConnection() {
        when(rxClient.connect()).thenReturn(observableConnectionObservable);

        nettyClient.connect();

        observableConnectionObservable.onNext(observableConnection);

        nettyClient.sendRequest(request);
        nettyClient.sendRequest(request);

        verify(observableConnection, times(2)).writeAndFlush(request);
    }
}