package de.d3adspace.rebekah.client.netty.handler;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.context.NettyMessageContext;
import de.d3adspace.rebekah.commons.kernel.Kernel;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import io.reactivex.netty.channel.ObservableConnection;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class ClientConnectionHandlerImpl implements ClientConnectionHandler {

  /**
   * The kernel that will handle the incoming messages.
   */
  private final Kernel kernel;

  @Inject
  public ClientConnectionHandlerImpl(Kernel kernel) {
    this.kernel = kernel;
  }

  @Override
  public void handleConnection(
    ObservableConnection<IncomingMessage, OutgoingMessage> observableConnection) {
    Observable<IncomingMessage> incomingMessageObservable = observableConnection
      .getInput();

    handleIncomingMessages(observableConnection, incomingMessageObservable);
  }

  /**
   * Handle the incoming messages delivered by the given observable.
   *
   * @param observableConnection      The connection.
   * @param incomingMessageObservable The observable.
   */
  private void handleIncomingMessages(
    ObservableConnection<IncomingMessage, OutgoingMessage> observableConnection,
    Observable<IncomingMessage> incomingMessageObservable) {
    incomingMessageObservable
      .subscribe(incomingMessage -> handleIncomingMessage(observableConnection,
        incomingMessage));
  }

  /**
   * Handle that a new message arrived.
   *
   * @param observableConnection The connection.
   * @param incomingMessage      The message.
   */
  private void handleIncomingMessage(
    ObservableConnection<IncomingMessage, OutgoingMessage> observableConnection,
    IncomingMessage incomingMessage) {
    MessageContext messageContext = new NettyMessageContext(
      observableConnection, incomingMessage);
    kernel.handleMessage(messageContext, incomingMessage);
  }
}
