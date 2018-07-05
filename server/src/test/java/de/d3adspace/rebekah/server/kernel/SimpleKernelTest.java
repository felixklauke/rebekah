package de.d3adspace.rebekah.server.kernel;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.request.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class SimpleKernelTest {

    @Mock
    private RequestContext requestContext;
    @Mock
    private Request request;

    private Kernel kernel;

    @BeforeEach
    void setUp() {
        kernel = new SimpleKernel();
    }

    @Test
    void testHandleRequest() {
        kernel.handleRequest(requestContext, request);
    }
}