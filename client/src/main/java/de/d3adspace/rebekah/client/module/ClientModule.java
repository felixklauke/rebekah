package de.d3adspace.rebekah.client.module;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import de.d3adspace.rebekah.client.RebekahClient;
import de.d3adspace.rebekah.client.RebekahClientImpl;
import de.d3adspace.rebekah.client.netty.NettyClient;
import de.d3adspace.rebekah.client.netty.pipeline.NettyPipelineConfigurator;
import de.d3adspace.rebekah.client.provider.RxClientProvider;
import de.d3adspace.rebekah.client.transport.TransportClient;
import de.d3adspace.rebekah.commons.module.CommonsModule;
import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.client.RxClient;
import io.reactivex.netty.pipeline.PipelineConfigurator;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class ClientModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("serverHost")).toInstance("localhost");
        bindConstant().annotatedWith(Names.named("serverPort")).to(8083);

        bind(RebekahClient.class).to(RebekahClientImpl.class).asEagerSingleton();
        bind(TransportClient.class).to(NettyClient.class).asEagerSingleton();

        bind(new TypeLiteral<PipelineConfigurator<Response, Request>>() {
        }).to(NettyPipelineConfigurator.class);
        bind(new TypeLiteral<RxClient<Request, Response>>() {
        }).toProvider(RxClientProvider.class);

        install(new CommonsModule());
    }
}
