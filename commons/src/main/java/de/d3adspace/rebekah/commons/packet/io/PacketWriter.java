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

  /**
   * Write the given integer.
   *
   * @param integer The integer.
   */
  void writeInt(int integer);

  /**
   * Write the given float.
   *
   * @param floatValue The value of the float
   */
  void writeFloat(float floatValue);

  /**
   * Write the given double.
   *
   * @param doubleValue The double.
   */
  void writeDouble(double doubleValue);
}
