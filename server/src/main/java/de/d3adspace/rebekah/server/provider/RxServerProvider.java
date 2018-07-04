package de.d3adspace.rebekah.server.provider;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.server.RxServer;
import rx.Observable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

/**
 * Provides an {@link RxServer}.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RxServerProvider implements Provider<RxServer<Request, Response>> {

    /**
     * The port of the server.
     */
    private final int serverPort;

    @Inject
    public RxServerProvider(@Named("serverPort") int serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public RxServer<Request, Response> get() {
        return RxNetty.createTcpServer(serverPort, pipeline -> {
        }, newConnection -> Observable.just(null));
    }
}
