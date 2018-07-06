package de.d3adspace.rebekah.client.netty;

import de.d3adspace.rebekah.client.transport.TransportClient;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyClient implements TransportClient {

    @Override
    public void connect() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void disconnect() {

    }
}
