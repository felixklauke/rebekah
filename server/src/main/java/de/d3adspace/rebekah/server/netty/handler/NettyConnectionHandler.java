package de.d3adspace.rebekah.server.netty.handler;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.channel.ConnectionHandler;
import io.reactivex.netty.channel.ObservableConnection;
import rx.Observable;

/**
 * Handling connections by RxNetty.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyConnectionHandler implements ConnectionHandler<Request, Response> {

    @Override
    public Observable<Void> handle(ObservableConnection<Request, Response> newConnection) {
        return Observable.just(null);
    }
}
