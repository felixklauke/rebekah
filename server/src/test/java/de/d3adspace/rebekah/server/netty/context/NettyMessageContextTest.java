package de.d3adspace.rebekah.server.netty.context;

import static org.mockito.Mockito.verify;

import de.d3adspace.rebekah.commons.context.NettyMessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import io.reactivex.netty.channel.ObservableConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class NettyMessageContextTest {

  @Mock
  private IncomingMessage request;
  @Mock
  private ObservableConnection<IncomingMessage, OutgoingMessage> observableConnection;
  @Mock
  private OutgoingMessage response;

  private NettyMessageContext nettyRequestContext;

  @BeforeEach
  void setUp() {
    nettyRequestContext = new NettyMessageContext(observableConnection,
      request);
  }

  @Test
  void testResume() {
    nettyRequestContext.resume(response);

    verify(observableConnection).writeAndFlush(response);
  }
}
