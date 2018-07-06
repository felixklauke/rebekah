package de.d3adspace.rebekah.server;

import de.d3adspace.rebekah.commons.agent.RebekahPacketAgent;
import de.d3adspace.rebekah.commons.handler.RequestHandler;
import de.d3adspace.rebekah.commons.handler.RequestHandlerManager;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.server.transport.TransportServer;

import javax.inject.Inject;

/**
 * Represents the rebekah server.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahServerImpl extends RebekahPacketAgent implements RebekahServer {

    /**
     * The underlying transporting server.
     */
    private final TransportServer transportServer;

    /**
     * The manager of all request managers.
     */
    private final RequestHandlerManager requestHandlerManager;

    /**
     * Create a new rebekah server instance by its underlying transport layer.
     *  @param transportServer The transport layer.
     * @param packetRegistry The packet registry.
     * @param requestHandlerManager The request handler manager.
     */
    @Inject
    public RebekahServerImpl(TransportServer transportServer, PacketRegistry packetRegistry, RequestHandlerManager requestHandlerManager) {
        super(packetRegistry);
        this.transportServer = transportServer;
        this.requestHandlerManager = requestHandlerManager;
    }

    @Override
    public void start() {
        transportServer.start();
    }

    @Override
    public void stop() {
        transportServer.stop();
    }

    @Override
    public boolean isRunning() {
        return transportServer.isRunning();
    }

    @Override
    public void registerRequestHandler(RequestHandler requestHandler) {
        requestHandlerManager.registerRequestHandler(requestHandler);
    }

    @Override
    public void unregisterRequestHandler(RequestHandler requestHandler) {
        requestHandlerManager.unregisterRequestHandler(requestHandler);
    }

    @Override
    public boolean isRequestHandlerRegistered(RequestHandler requestHandler) {
        return requestHandlerManager.isRequestHandlerRegistered(requestHandler);
    }
}
