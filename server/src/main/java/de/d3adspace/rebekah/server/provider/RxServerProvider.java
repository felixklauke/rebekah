package de.d3adspace.rebekah.server.provider;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.channel.ConnectionHandler;
import io.reactivex.netty.pipeline.PipelineConfigurator;
import io.reactivex.netty.server.RxServer;

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

    /**
     * The configurator of the netty pipeline.
     */
    private final PipelineConfigurator<Request, Response> pipelineConfigurator;

    /**
     * The connection handler of the netty server.
     */
    private final ConnectionHandler<Request, Response> connectionHandler;

    @Inject
    public RxServerProvider(@Named("serverPort") int serverPort, PipelineConfigurator<Request, Response> pipelineConfigurator, ConnectionHandler<Request, Response> connectionHandler) {
        this.serverPort = serverPort;
        this.pipelineConfigurator = pipelineConfigurator;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public RxServer<Request, Response> get() {
        return RxNetty.createTcpServer(serverPort, pipelineConfigurator, connectionHandler);
    }
}
