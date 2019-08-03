package de.d3adspace.rebekah.client.netty.handler;

import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import io.reactivex.netty.channel.ObservableConnection;

/**
 * Handles newly built connections.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface ClientConnectionHandler {

  /**
   * Handle the establishment of the given connection.
   *
   * @param observableConnection The connection.
   */
  void handleConnection(
      ObservableConnection<IncomingMessage, OutgoingMessage> observableConnection);
}
