package de.d3adspace.rebekah.commons.context;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the {@link AbstractRequestContext}.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
class AbstractRequestContextTest {

    /**
     * Mocked test request.
     */
    private static final Request TEST_REQUEST = new Request() {
    };

    /**
     * The request context test subject.
     */
    private AbstractRequestContext abstractRequestContext;

    @BeforeEach
    void setUp() {
        abstractRequestContext = new AbstractRequestContext<Request>(TEST_REQUEST) {
            @Override
            public void resume(Response response) {
            }
        };
    }

    @Test
    void testGetRequest() {
        Request request = abstractRequestContext.getRequest();
        assertEquals(TEST_REQUEST, request);
    }
}