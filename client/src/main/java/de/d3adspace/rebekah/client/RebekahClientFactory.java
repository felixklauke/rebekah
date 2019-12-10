package de.d3adspace.rebekah.client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.d3adspace.rebekah.client.module.ClientModule;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahClientFactory {

  RebekahClientFactory() {
    throw new AssertionError("Can't instantiate factories.");
  }

  public static RebekahClient createClient() {
    Injector injector = Guice.createInjector(new ClientModule());
    return injector.getInstance(RebekahClient.class);
  }
}
