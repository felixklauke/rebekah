package de.d3adspace.rebekah.server;

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
     * Create a new rebekah server instance by its underlying transport layer.
     *
     * @param transportServer The transport layer.
     */
    @Inject
    public RebekahServerImpl(TransportServer transportServer) {
        this.transportServer = transportServer;
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
}
