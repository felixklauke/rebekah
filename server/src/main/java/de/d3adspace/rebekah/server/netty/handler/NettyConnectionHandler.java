package de.d3adspace.rebekah.server.netty.handler;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import de.d3adspace.rebekah.server.kernel.Kernel;
import de.d3adspace.rebekah.server.netty.context.NettyMessageContext;
import io.reactivex.netty.channel.ConnectionHandler;
import io.reactivex.netty.channel.ObservableConnection;
import rx.Observable;

import javax.inject.Inject;

/**
 * Handling connections and input by RxNetty.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyConnectionHandler implements ConnectionHandler<IncomingMessage, OutgoingMessage> {

    /**
     * The kernel that will process the request in its context.
     */
    private final Kernel kernel;

    @Inject
    NettyConnectionHandler(Kernel kernel) {
        this.kernel = kernel;
    }

    @Override
    public Observable<Void> handle(ObservableConnection<IncomingMessage, OutgoingMessage> newConnection) {
        Observable<IncomingMessage> connectionInput = newConnection.getInput();

        connectionInput.subscribe(request -> {
            MessageContext messageContext = new NettyMessageContext(newConnection, request);
            kernel.handleRequest(messageContext, request);
        });

        return Observable.just(null);
    }
}
