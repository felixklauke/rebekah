package de.d3adspace.rebekah.client.provider;

import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.client.RxClient;
import io.reactivex.netty.pipeline.PipelineConfigurator;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RxClientProvider implements
  Provider<RxClient<OutgoingMessage, IncomingMessage>> {

  /**
   * The host of the server to connect to.
   */
  private final String serverHost;

  /**
   * The port of the server to connect to.
   */
  private final int serverPort;

  /**
   * The configurator of the netty pipeline.
   */
  private final PipelineConfigurator<IncomingMessage, OutgoingMessage> pipelineConfigurator;

  @Inject
  public RxClientProvider(
    @Named("serverHost") String serverHost,
    @Named("serverPort") int serverPort,
    PipelineConfigurator<IncomingMessage, OutgoingMessage> pipelineConfigurator
  ) {
    this.serverHost = serverHost;
    this.serverPort = serverPort;
    this.pipelineConfigurator = pipelineConfigurator;
  }

  @Override
  public RxClient<OutgoingMessage, IncomingMessage> get() {
    return RxNetty
      .createTcpClient(serverHost, serverPort, pipelineConfigurator);
  }
}
