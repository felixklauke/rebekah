package de.d3adspace.rebekah.client.netty.handler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.kernel.Kernel;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import io.reactivex.netty.channel.ObservableConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rx.subjects.BehaviorSubject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class ClientConnectionHandlerImplTest {

  @Mock
  Kernel kernel;
  @Mock
  ObservableConnection<IncomingMessage, OutgoingMessage> observableConnection;
  @Mock
  IncomingMessage incomingMessage;

  private BehaviorSubject<IncomingMessage> incomingMessageObservable = BehaviorSubject.create();
  private ClientConnectionHandler clientConnectionHandler;

  @BeforeEach
  void setUp() {
    clientConnectionHandler = new ClientConnectionHandlerImpl(kernel);
  }

  @Test
  void testHandleConnection() {
    Mockito.when(observableConnection.getInput()).thenReturn(incomingMessageObservable);

    clientConnectionHandler.handleConnection(observableConnection);

    incomingMessageObservable.onNext(incomingMessage);

    verify(kernel).handleMessage(any(MessageContext.class), eq(incomingMessage));
  }
}
