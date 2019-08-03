package de.d3adspace.rebekah.commons.context;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test for the {@link AbstractMessageContext}.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class AbstractMessageContextTest {

  @Mock
  private IncomingMessage testRequest;

  /**
   * The request context test subject.
   */
  private AbstractMessageContext abstractRequestContext;

  @BeforeEach
  void setUp() {
    abstractRequestContext = new AbstractMessageContext<IncomingMessage>(testRequest) {
      @Override
      public void resume(OutgoingMessage response) {
      }
    };
  }

  @Test
  void testGetRequest() {
    IncomingMessage request = abstractRequestContext.getRequest();
    assertEquals(testRequest, request,
        "The test request doesn't equal the one got from the context.");
  }
}
