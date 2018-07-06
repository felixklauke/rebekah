package de.d3adspace.rebekah.server;

import de.d3adspace.rebekah.commons.handler.RequestHandler;
import de.d3adspace.rebekah.commons.handler.RequestHandlerManager;
import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.server.transport.TransportServer;

import javax.inject.Inject;

/**
 * Represents the rebekah server.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahServerImpl implements RebekahServer {

    /**
     * The underlying transporting server.
     */
    private final TransportServer transportServer;

    /**
     * The registry of all known packets.
     */
    private final PacketRegistry packetRegistry;

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
        this.transportServer = transportServer;
        this.packetRegistry = packetRegistry;
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
    public void registerPacket(Class<? extends Packet> packetClass) {
        packetRegistry.registerPacket(packetClass);
    }

    @Override
    public void unregisterPacket(Class<? extends Packet> packetClass) {
        packetRegistry.unregisterPacket(packetClass);
    }

    @Override
    public boolean isPacketRegistered(Class<? extends Packet> packetClass) {
        return packetRegistry.isPacketRegistered(packetClass);
    }
}
