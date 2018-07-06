package de.d3adspace.rebekah.client.module;

import com.google.inject.AbstractModule;
import de.d3adspace.rebekah.client.RebekahClient;
import de.d3adspace.rebekah.client.RebekahClientImpl;
import de.d3adspace.rebekah.client.netty.NettyClient;
import de.d3adspace.rebekah.client.transport.TransportClient;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class ClientModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RebekahClient.class).to(RebekahClientImpl.class).asEagerSingleton();
        bind(TransportClient.class).to(NettyClient.class).asEagerSingleton();
    }
}
