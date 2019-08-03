package de.d3adspace.rebekah.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
class RebekahClientFactoryTest {

  @Test
  void testInstantiation() {
    Executable executable = RebekahClientFactory::new;
    assertThrows(AssertionError.class, executable);
  }

  @Test
  void testCreateClient() {
    RebekahClient rebekahClient = RebekahClientFactory.createClient();

    assertNotNull(rebekahClient, "Client should not be null.");
  }
}
