package de.d3adspace.rebekah.client;

import de.d3adspace.rebekah.client.transport.TransportClient;
import de.d3adspace.rebekah.commons.agent.RebekahPacketAgent;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;

import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahClientImpl extends RebekahPacketAgent implements RebekahClient {

    /**
     * The underlying transport client.
     */
    private final TransportClient transportClient;

    @Inject
    public RebekahClientImpl(PacketRegistry packetRegistry, TransportClient transportClient, IncomingMessageHandlerManager incomingMessageHandlerManager) {
        super(packetRegistry, incomingMessageHandlerManager);
        this.transportClient = transportClient;
    }

    @Override
    public void connect() {
        transportClient.connect();
    }

    @Override
    public boolean isConnected() {
        return transportClient.isConnected();
    }

    @Override
    public void disconnect() {
        transportClient.disconnect();
    }

    @Override
    public void sendMessage(OutgoingMessage outgoingMessage) {
        transportClient.sendRequest(outgoingMessage);
    }
}
