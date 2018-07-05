package de.d3adspace.rebekah.commons.packet.description;

import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.description.annotation.PacketMeta;

/**
 * An {@link PacketDescription} based on the {@link PacketMeta} annotation.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class MetaBasedPacketDescription implements PacketDescription {

    /**
     * The meta taken from the packets class.
     */
    private final PacketMeta packetMeta;

    /**
     * The class of the packet.
     */
    private final Class<? extends Packet> packetClass;

    /**
     * Create a new meta based packet description by the reflected annotation and its source class.
     *
     * @param packetMeta  The reflected annotation.
     * @param packetClass The class of the packet.
     */
    MetaBasedPacketDescription(PacketMeta packetMeta, Class<? extends Packet> packetClass) {
        this.packetMeta = packetMeta;
        this.packetClass = packetClass;
    }

    /**
     * Create a new packet description by taking an annotation from the given class.
     *
     * @param packetClass The class of the packet.
     *
     * @return The packet description.
     */
    public static PacketDescription createPacketDescription(Class<? extends Packet> packetClass) {
        PacketMeta packetMeta = packetClass.getAnnotation(PacketMeta.class);

        if (packetMeta == null) {
            throw new IllegalStateException("Tried to create meta based packet description but @PacketMeta is not present at " + packetClass);
        }

        return new MetaBasedPacketDescription(packetMeta, packetClass);
    }

    @Override
    public int getId() {
        return packetMeta.id();
    }

    @Override
    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }
}
