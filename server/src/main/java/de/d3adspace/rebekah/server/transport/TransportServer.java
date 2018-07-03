package de.d3adspace.rebekah.server.transport;

/**
 * Represents the transport layer of the rebekah server.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface TransportServer {

    /**
     * Start the server on the given port.
     *
     * @param port The port.
     */
    void start(int port);

    /**
     * Stop the server.
     */
    void stop();

    /**
     * Check if the server is running.
     *
     * @return If the server is running.
     */
    boolean isRunning();
}
