package de.d3adspace.rebekah.server;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
class RebekahServerFactoryTest {

  @Test
  void testInstantiation() {
    Executable executable = RebekahServerFactory::new;
    assertThrows(AssertionError.class, executable);
  }

  @Test
  void testCreateServer() {
    RebekahServer server = RebekahServerFactory.createServer();

    assertNotNull(server, "Server should not be null.");
  }
}
