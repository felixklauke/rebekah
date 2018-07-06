package de.d3adspace.rebekah.server.kernel;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.handler.RequestHandlerManager;
import de.d3adspace.rebekah.commons.request.Request;
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
    private RequestHandlerManager requestHandlerManager;
    @Mock
    private RequestContext requestContext;
    @Mock
    private Request request;

    private Kernel kernel;

    @BeforeEach
    void setUp() {
        kernel = new SimpleKernel(requestHandlerManager);
    }

    @Test
    void testHandleRequest() {
        kernel.handleRequest(requestContext, request);

        verify(requestHandlerManager).process(requestContext, request);
    }
}