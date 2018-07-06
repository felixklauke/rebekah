package de.d3adspace.rebekah.commons.agent;

import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class RebekahPacketAgentTest {

    @Mock
    PacketRegistry packetRegistry;

    private RebekahPacketAgent rebekahPacketAgent;

    @BeforeEach
    void setUp() {
        rebekahPacketAgent = new RebekahPacketAgent(packetRegistry);
    }

    @Test
    void testGetPacketRegistry() {
        assertEquals(packetRegistry, rebekahPacketAgent.getPacketRegistry());
    }
}