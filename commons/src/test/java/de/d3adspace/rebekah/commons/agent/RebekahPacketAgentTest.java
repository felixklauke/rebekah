package de.d3adspace.rebekah.commons.agent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.d3adspace.rebekah.commons.handler.IncomingMessageHandler;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class RebekahPacketAgentTest {

  @Mock
  PacketRegistry packetRegistry;
  @Mock
  IncomingMessageHandlerManager incomingMessageHandlerManager;
  @Mock
  IncomingMessageHandler incomingMessageHandler;

  private RebekahPacketAgent rebekahPacketAgent;

  @BeforeEach
  void setUp() {
    rebekahPacketAgent = new RebekahPacketAgent(packetRegistry, incomingMessageHandlerManager);
  }

  @Test
  void testGetPacketRegistry() {
    assertEquals(packetRegistry, rebekahPacketAgent.getPacketRegistry());
  }


  @Test
  void testRegisterRequestHandler() {
    rebekahPacketAgent.registerMessageHandler(incomingMessageHandler);

    verify(incomingMessageHandlerManager).registerMessageHandler(incomingMessageHandler);
  }

  @Test
  void testUnregisterRequestHandler() {
    rebekahPacketAgent.unregisterMessageHandler(incomingMessageHandler);

    verify(incomingMessageHandlerManager).unregisterMessageHandler(incomingMessageHandler);
  }

  @Test
  void testIsRequestHandlerRegistered() {
    boolean shouldBeRegistered = true;
    when(incomingMessageHandlerManager.isMessageHandlerRegistered(incomingMessageHandler))
        .thenReturn(shouldBeRegistered);

    boolean requestHandlerRegistered = rebekahPacketAgent
        .isMessageHandlerRegistered(incomingMessageHandler);

    verify(incomingMessageHandlerManager).isMessageHandlerRegistered(incomingMessageHandler);
    assertEquals(shouldBeRegistered, requestHandlerRegistered,
        "Request handler registered state differs.");
  }
}
