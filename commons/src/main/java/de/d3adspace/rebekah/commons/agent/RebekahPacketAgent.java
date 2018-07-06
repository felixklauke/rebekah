package de.d3adspace.rebekah.commons.agent;

import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahPacketAgent implements PacketAgent {

    /**
     * The registry of all known packets.
     */
    private final PacketRegistry packetRegistry;

    /**
     * Create a new packet agent by its underlying registry.
     *
     * @param packetRegistry The packet registry.
     */
    public RebekahPacketAgent(PacketRegistry packetRegistry) {
        this.packetRegistry = packetRegistry;
    }

    @Override
    public PacketRegistry getPacketRegistry() {
        return packetRegistry;
    }

    @Override
    public void registerPacket(Class<? extends Packet> packetClass) {
        packetRegistry.registerPacket(packetClass);
    }

    @Override
    public void unregisterPacket(Class<? extends Packet> packetClass) {
        packetRegistry.unregisterPacket(packetClass);
    }

    @Override
    public boolean isPacketRegistered(Class<? extends Packet> packetClass) {
        return packetRegistry.isPacketRegistered(packetClass);
    }
}
