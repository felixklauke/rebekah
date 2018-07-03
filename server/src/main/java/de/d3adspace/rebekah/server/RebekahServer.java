package de.d3adspace.rebekah.server;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface RebekahServer {

    /**
     * Start the server.
     */
    void start();

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
