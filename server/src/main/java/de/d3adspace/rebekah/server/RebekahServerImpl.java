package de.d3adspace.rebekah.server;

import de.d3adspace.rebekah.commons.agent.RebekahPacketAgent;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.server.transport.TransportServer;
import javax.inject.Inject;

/**
 * Represents the rebekah server.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahServerImpl extends RebekahPacketAgent implements
  RebekahServer {

  /**
   * The underlying transporting server.
   */
  private final TransportServer transportServer;

  /**
   * Create a new rebekah server instance by its underlying transport layer.
   *
   * @param transportServer               The transport layer.
   * @param packetRegistry                The packet registry.
   * @param incomingMessageHandlerManager The request handler manager.
   */
  @Inject
  public RebekahServerImpl(
    TransportServer transportServer,
    PacketRegistry packetRegistry,
    IncomingMessageHandlerManager incomingMessageHandlerManager
  ) {
    super(packetRegistry, incomingMessageHandlerManager);
    this.transportServer = transportServer;
  }

  @Override
  public void start() {
    transportServer.start();
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
