package de.d3adspace.rebekah.client.provider;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.client.RxClient;
import io.reactivex.netty.pipeline.PipelineConfigurator;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RxClientProvider implements Provider<RxClient<Request, Response>> {

    /**
     * The host of the server to connect to.
     */
    private final String serverHost;

    /**
     * The port of the server to connect to.
     */
    private final int serverPort;

    /**
     * The configurator of the netty pipeline.
     */
    private final PipelineConfigurator<Response, Request> pipelineConfigurator;

    @Inject
    public RxClientProvider(@Named("serverHost") String serverHost, @Named("serverPort") int serverPort, PipelineConfigurator<Response, Request> pipelineConfigurator) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.pipelineConfigurator = pipelineConfigurator;
    }

    @Override
    public RxClient<Request, Response> get() {
        return RxNetty.createTcpClient(serverHost, serverPort, pipelineConfigurator);
    }
}
