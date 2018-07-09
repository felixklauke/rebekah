package de.d3adspace.rebekah.client.netty;

import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import io.reactivex.netty.channel.ObservableConnection;
import io.reactivex.netty.client.RxClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rx.subjects.BehaviorSubject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class NettyClientTest {

    @Mock
    ObservableConnection<IncomingMessage, OutgoingMessage> observableConnection;
    @Mock
    OutgoingMessage request;
    @Mock
    RxClient<OutgoingMessage, IncomingMessage> rxClient;
    private BehaviorSubject<ObservableConnection<IncomingMessage, OutgoingMessage>> observableConnectionObservable = BehaviorSubject.create();

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
    void testDisconnectWithException() {
        Executable executable = () -> nettyClient.disconnect();
        assertThrows(IllegalStateException.class, executable);
    }

    @Test
    void testDisconnect() {
        when(rxClient.connect()).thenReturn(observableConnectionObservable);
        nettyClient.connect();
        observableConnectionObservable.onNext(observableConnection);

        nettyClient.disconnect();

        verify(rxClient).shutdown();
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