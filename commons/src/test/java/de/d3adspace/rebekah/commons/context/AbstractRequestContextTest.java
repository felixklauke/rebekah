package de.d3adspace.rebekah.commons.context;

import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
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
    private IncomingMessage testRequest;

    /**
     * The request context test subject.
     */
    private AbstractRequestContext abstractRequestContext;

    @BeforeEach
    void setUp() {
        abstractRequestContext = new AbstractRequestContext<IncomingMessage>(testRequest) {
            @Override
            public void resume(OutgoingMessage response) {
            }
        };
    }

    @Test
    void testGetRequest() {
        IncomingMessage request = abstractRequestContext.getRequest();
        assertEquals(testRequest, request, "The test request doesn't equal the one got from the context.");
    }
}