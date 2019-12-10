package de.d3adspace.rebekah.server.module;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import de.d3adspace.rebekah.commons.kernel.Kernel;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import de.d3adspace.rebekah.commons.module.CommonsModule;
import de.d3adspace.rebekah.server.RebekahServer;
import de.d3adspace.rebekah.server.RebekahServerImpl;
import de.d3adspace.rebekah.server.kernel.ServerKernel;
import de.d3adspace.rebekah.server.netty.NettyServerImpl;
import de.d3adspace.rebekah.server.netty.handler.NettyConnectionHandler;
import de.d3adspace.rebekah.server.netty.pipeline.NettyPipelineConfigurator;
import de.d3adspace.rebekah.server.provider.RxServerProvider;
import de.d3adspace.rebekah.server.transport.TransportServer;
import io.reactivex.netty.channel.ConnectionHandler;
import io.reactivex.netty.pipeline.PipelineConfigurator;
import io.reactivex.netty.server.RxServer;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahServerModule extends AbstractModule {

  @Override
  protected void configure() {
    bindConstant().annotatedWith(Names.named("serverPort")).to(8081);

    // The main server entry point
    bind(RebekahServer.class).to(RebekahServerImpl.class).asEagerSingleton();

    // Transport layer
    bind(TransportServer.class).to(NettyServerImpl.class).asEagerSingleton();

    // Kernel
    bind(Kernel.class).to(ServerKernel.class).asEagerSingleton();

    // Rx Server
    bind(
      new TypeLiteral<PipelineConfigurator<IncomingMessage, OutgoingMessage>>() {
      }).to(NettyPipelineConfigurator.class);
    bind(
      new TypeLiteral<ConnectionHandler<IncomingMessage, OutgoingMessage>>() {
      }).to(NettyConnectionHandler.class);
    bind(new TypeLiteral<RxServer<IncomingMessage, OutgoingMessage>>() {
    }).toProvider(RxServerProvider.class);

    // Install foreign modules
    install(new CommonsModule());
  }
}
