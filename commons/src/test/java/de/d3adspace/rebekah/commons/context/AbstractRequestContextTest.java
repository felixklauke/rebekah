package de.d3adspace.rebekah.commons.context;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the {@link AbstractRequestContext}.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class AbstractRequestContextTest {

    @Mock
    private Request testRequest;

    /**
     * The request context test subject.
     */
    private AbstractRequestContext abstractRequestContext;

    @BeforeEach
    void setUp() {
        abstractRequestContext = new AbstractRequestContext<Request>(testRequest) {
            @Override
            public void resume(Response response) {
            }
        };
    }

    @Test
    void testGetRequest() {
        Request request = abstractRequestContext.getRequest();
        assertEquals(testRequest, request, "The test request doesn't equal the one got from the context.");
    }
}