package de.d3adspace.rebekah.client.kernel;

import static org.mockito.Mockito.verify;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.kernel.Kernel;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class ClientKernelTest {

  @Mock
  MessageContext messageContext;
  @Mock
  IncomingMessage message;
  @Mock
  IncomingMessageHandlerManager incomingMessageHandlerManager;

  private Kernel kernel;

  @BeforeEach
  void setUp() {
    kernel = new ClientKernel(incomingMessageHandlerManager);
  }

  @Test
  void testHandleMessage() {
    kernel.handleMessage(messageContext, message);

    verify(incomingMessageHandlerManager).process(messageContext, message);
  }
}
