package de.d3adspace.rebekah.client.netty;

import de.d3adspace.rebekah.client.transport.TransportClient;
import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.channel.ObservableConnection;
import io.reactivex.netty.client.RxClient;
import rx.Observable;

import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyClient implements TransportClient {

    /**
     * The underlying rx server.
     */
    private final RxClient<Request, Response> rxClient;
    private Observable<ObservableConnection<Response, Request>> observableConnectionObservable;
    private boolean connected;

    @Inject
    public NettyClient(RxClient<Request, Response> rxClient) {
        this.rxClient = rxClient;
    }

    @Override
    public void connect() {
        observableConnectionObservable = rxClient.connect();
        observableConnectionObservable.subscribe(responseRequestObservableConnection -> {
            connected = true;
        });
    }

    @Override
    public boolean isConnected() {
        if (observableConnectionObservable == null) {
            return false;
        }

        return connected;
    }

    @Override
    public void disconnect() {
        rxClient.shutdown();
        connected = false;
    }

    @Override
    public void sendRequest(Request request) {
        observableConnectionObservable.subscribe(responseRequestObservableConnection -> {
            responseRequestObservableConnection.writeAndFlush(request);
        });
    }
}
