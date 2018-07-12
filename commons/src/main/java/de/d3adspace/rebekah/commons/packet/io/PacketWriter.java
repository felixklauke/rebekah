package de.d3adspace.rebekah.commons.packet.io;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface PacketWriter {

    /**
     * Write the given string.
     *
     * @param string The string.
     */
    void writeString(String string);
}
