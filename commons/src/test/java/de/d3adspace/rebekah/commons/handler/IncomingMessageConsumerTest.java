package de.d3adspace.rebekah.commons.handler;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class IncomingMessageConsumerTest {

  @Mock
  Method method;
  @Mock
  IncomingMessageHandler incomingMessageHandler;
  @Mock
  MessageContext messageContext;
  @Mock
  IncomingMessage incomingMessage;

  private IncomingMessageConsumer incomingMessageConsumer;

  @BeforeEach
  void setUp() {
    incomingMessageConsumer = new IncomingMessageConsumer(
      incomingMessageHandler, method);
  }

  @Test
  void testAccept() {
    incomingMessageConsumer.accept(messageContext, incomingMessage);

    try {
      verify(method)
        .invoke(incomingMessageHandler, messageContext, incomingMessage);
    } catch (IllegalAccessException | InvocationTargetException e) {
      fail(e);
    }
  }

  @Test
  void testAcceptWithException() {
    try {
      when(
        method.invoke(incomingMessageHandler, messageContext, incomingMessage))
        .thenThrow(new IllegalAccessException());
    } catch (IllegalAccessException | InvocationTargetException e) {
      fail(e);
    }

    Executable executable = () -> incomingMessageConsumer
      .accept(messageContext, incomingMessage);

    assertThrows(IllegalStateException.class, executable);
  }
}
