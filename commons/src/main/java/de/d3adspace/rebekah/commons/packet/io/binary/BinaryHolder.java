package de.d3adspace.rebekah.commons.packet.io.binary;

import io.netty.buffer.ByteBuf;

/**
 * Abstraction of an container of a {@link ByteBuf}.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
class BinaryHolder {

    /**
     * The contained byte buf.
     */
    private final ByteBuf byteBuf;

    /**
     * Create a new binary holder by its underlying binary.
     *
     * @param byteBuf The binary.
     */
    BinaryHolder(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    /**
     * Get the underlying byte buf.
     *
     * @return The byte buf.
     */
    ByteBuf getByteBuf() {
        return byteBuf;
    }
}
