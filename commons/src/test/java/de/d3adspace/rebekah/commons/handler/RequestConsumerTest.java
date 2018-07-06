package de.d3adspace.rebekah.commons.handler;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.request.Request;
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
class RequestConsumerTest {

    @Mock
    Method method;
    @Mock
    RequestHandler requestHandler;
    @Mock
    RequestContext requestContext;
    @Mock
    Request request;

    private RequestConsumer requestConsumer;

    @BeforeEach
    void setUp() {
        requestConsumer = new RequestConsumer(requestHandler, method);
    }

    @Test
    void testAccept() {
        requestConsumer.accept(requestContext, request);

        try {
            verify(method).invoke(requestHandler, requestContext, request);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }

    @Test
    void testAcceptWithException() {
        try {
            when(method.invoke(requestHandler, requestContext, request)).thenThrow(new IllegalAccessException());
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }

        Executable executable = () -> requestConsumer.accept(requestContext, request);

        assertThrows(IllegalStateException.class, executable);
    }
}