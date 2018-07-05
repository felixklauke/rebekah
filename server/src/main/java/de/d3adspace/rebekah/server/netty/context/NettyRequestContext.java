package de.d3adspace.rebekah.server.netty.context;

import de.d3adspace.rebekah.commons.context.AbstractRequestContext;
import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.channel.ObservableConnection;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyRequestContext extends AbstractRequestContext<Request> {

    /**
     * The connection of the context where the request comes from.
     */
    private final ObservableConnection<Request, Response> connection;

    /**
     * Create a new request context by its underlying request.
     *
     * @param connection The connection needed for sending back the response.
     * @param request    The request.
     */
    public NettyRequestContext(ObservableConnection<Request, Response> connection, Request request) {
        super(request);
        this.connection = connection;
    }

    @Override
    public void resume(Response response) {
        connection.writeAndFlush(response);
    }
}
