package de.d3adspace.rebekah.server.provider;

import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.channel.ConnectionHandler;
import io.reactivex.netty.pipeline.PipelineConfigurator;
import io.reactivex.netty.server.RxServer;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

/**
 * Provides an {@link RxServer}.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RxServerProvider implements
  Provider<RxServer<IncomingMessage, OutgoingMessage>> {

  /**
   * The port of the server.
   */
  private final int serverPort;

  /**
   * The configurator of the netty pipeline.
   */
  private final PipelineConfigurator<IncomingMessage, OutgoingMessage> pipelineConfigurator;

  /**
   * The connection handler of the netty server.
   */
  private final ConnectionHandler<IncomingMessage, OutgoingMessage> connectionHandler;

  @Inject
  public RxServerProvider(@Named("serverPort") int serverPort,
    PipelineConfigurator<IncomingMessage, OutgoingMessage> pipelineConfigurator,
    ConnectionHandler<IncomingMessage, OutgoingMessage> connectionHandler) {
    this.serverPort = serverPort;
    this.pipelineConfigurator = pipelineConfigurator;
    this.connectionHandler = connectionHandler;
  }

  @Override
  public RxServer<IncomingMessage, OutgoingMessage> get() {
    return RxNetty
      .createTcpServer(serverPort, pipelineConfigurator, connectionHandler);
  }
}
