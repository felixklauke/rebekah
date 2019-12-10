package de.d3adspace.rebekah.server.netty.handler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import rx.Observable;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class NettyConnectionHandlerTest {

  @Mock
  IncomingMessage request;
  @Mock
  Kernel kernel;
  @Mock
  ObservableConnection<IncomingMessage, OutgoingMessage> observableConnection;

  private NettyConnectionHandler nettyConnectionHandler;

  @BeforeEach
  void setUp() {
    nettyConnectionHandler = new NettyConnectionHandler(kernel);
  }

  @Test
  void testHandle() {
    Observable<IncomingMessage> requestObservable = Observable.just(request);

    when(observableConnection.getInput()).thenReturn(requestObservable);

    Observable<Void> handle = nettyConnectionHandler
      .handle(observableConnection);

    verify(kernel)
      .handleMessage(Mockito.any(MessageContext.class), eq(request));

    assertNotNull(handle,
      "Observable of handled connection should not be null.");
  }
}
