package de.d3adspace.rebekah.commons.handler;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    RequestContext requestContext;
    @Mock
    IncomingMessage incomingMessage;

    private IncomingMessageConsumer incomingMessageConsumer;

    @BeforeEach
    void setUp() {
        incomingMessageConsumer = new IncomingMessageConsumer(incomingMessageHandler, method);
    }

    @Test
    void testAccept() {
        incomingMessageConsumer.accept(requestContext, incomingMessage);

        try {
            verify(method).invoke(incomingMessageHandler, requestContext, incomingMessage);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }

    @Test
    void testAcceptWithException() {
        try {
            when(method.invoke(incomingMessageHandler, requestContext, incomingMessage)).thenThrow(new IllegalAccessException());
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }

        Executable executable = () -> incomingMessageConsumer.accept(requestContext, incomingMessage);

        assertThrows(IllegalStateException.class, executable);
    }
}