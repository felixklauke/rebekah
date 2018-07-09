package de.d3adspace.rebekah.server.kernel;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class SimpleKernelTest {

    @Mock
    private IncomingMessageHandlerManager incomingMessageHandlerManager;
    @Mock
    private MessageContext messageContext;
    @Mock
    private IncomingMessage request;

    private Kernel kernel;

    @BeforeEach
    void setUp() {
        kernel = new SimpleKernel(incomingMessageHandlerManager);
    }

    @Test
    void testHandleRequest() {
        kernel.handleRequest(messageContext, request);

        verify(incomingMessageHandlerManager).process(messageContext, request);
    }
}