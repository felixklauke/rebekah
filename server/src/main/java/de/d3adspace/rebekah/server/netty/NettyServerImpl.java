package de.d3adspace.rebekah.server.netty;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import de.d3adspace.rebekah.server.transport.TransportServer;
import io.netty.channel.ChannelPipeline;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.channel.ConnectionHandler;
import io.reactivex.netty.channel.ObservableConnection;
import io.reactivex.netty.pipeline.PipelineConfigurator;
import io.reactivex.netty.server.RxServer;
import rx.Observable;

/**
 * Default implementation of the transport layer represented by the {@link TransportServer} based on netty. For netty
 * usage the Wrapper {@link RxNetty} is used.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyServerImpl implements TransportServer, PipelineConfigurator<Request, Response>, ConnectionHandler<Request, Response> {

    static {
        RxNetty.useNativeTransportIfApplicable();
    }

    private boolean running = false;
    private RxServer<Request, Response> rxServer;

    @Override
    public void start(int port) {
        running = true;
        rxServer = RxNetty.createTcpServer(port, this, this);
    }

    @Override
    public void stop() {
        running = false;

        try {
            rxServer.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isRunning() {
        return rxServer != null && running;
    }

    @Override
    public void configureNewPipeline(ChannelPipeline pipeline) {

    }

    @Override
    public Observable<Void> handle(ObservableConnection<Request, Response> newConnection) {
        return null;
    }
}
