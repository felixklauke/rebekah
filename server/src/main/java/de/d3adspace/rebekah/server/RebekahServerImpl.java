package de.d3adspace.rebekah.server;

import de.d3adspace.rebekah.server.transport.TransportServer;

/**
 * Represents the rebekah server.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahServerImpl implements RebekahServer {

    /**
     * The port of the server.
     */
    private final int serverPort;

    /**
     * The underlying transporting server.
     */
    private final TransportServer transportServer;

    /**
     * Create a new rebekah server instance by its underlying transport layer.
     *
     * @param serverPort      The port of the server.
     * @param transportServer The transport layer.
     */
    public RebekahServerImpl(int serverPort, TransportServer transportServer) {
        this.serverPort = serverPort;
        this.transportServer = transportServer;
    }

    @Override
    public void start() {
        transportServer.start(serverPort);
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
