package de.d3adspace.rebekah.client.netty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class NettyClientTest {

    private NettyClient nettyClient;

    @BeforeEach
    void setUp() {
        nettyClient = new NettyClient();
    }

    @Test
    void testConnect() {
        nettyClient.connect();
    }

    @Test
    void testIsConnected() {
        nettyClient.isConnected();
    }

    @Test
    void testDisconnect() {
        nettyClient.disconnect();
    }
}