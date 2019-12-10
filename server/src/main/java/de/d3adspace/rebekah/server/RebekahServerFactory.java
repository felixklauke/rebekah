package de.d3adspace.rebekah.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.d3adspace.rebekah.server.module.RebekahServerModule;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahServerFactory {

  /**
   * Package local constructor that will never be called.
   */
  RebekahServerFactory() {
    throw new AssertionError("Can't instantiate factories.");
  }

  /**
   * Create a new rebekah server instance.
   *
   * @return The rebekah server.
   */
  public static RebekahServer createServer() {
    Injector injector = Guice.createInjector(new RebekahServerModule());
    return injector.getInstance(RebekahServer.class);
  }
}
