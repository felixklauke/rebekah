package de.d3adspace.rebekah.server.netty.context;

import de.d3adspace.rebekah.commons.context.AbstractMessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import io.reactivex.netty.channel.ObservableConnection;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyMessageContext extends AbstractMessageContext<IncomingMessage> {

    /**
     * The connection of the context where the request comes from.
     */
    private final ObservableConnection<IncomingMessage, OutgoingMessage> connection;

    /**
     * Create a new request context by its underlying request.
     *
     * @param connection The connection needed for sending back the response.
     * @param request    The request.
     */
    public NettyMessageContext(ObservableConnection<IncomingMessage, OutgoingMessage> connection, IncomingMessage request) {
        super(request);
        this.connection = connection;
    }

    @Override
    public void resume(OutgoingMessage response) {
        connection.writeAndFlush(response);
    }
}
