package de.d3adspace.rebekah.server.kernel;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.kernel.Kernel;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class ServerKernel implements Kernel {

  /**
   * The packet handler manager with the overview over all packet handlers.
   */
  private final IncomingMessageHandlerManager incomingMessageHandlerManager;

  /**
   * Create a new kernel instance by its underlying packet handler manager.
   *
   * @param incomingMessageHandlerManager The packet handler manager.
   */
  @Inject
  public ServerKernel(IncomingMessageHandlerManager incomingMessageHandlerManager) {
    this.incomingMessageHandlerManager = incomingMessageHandlerManager;
  }

  @Override
  public void handleMessage(MessageContext messageContext, IncomingMessage request) {
    incomingMessageHandlerManager.process(messageContext, request);
  }
}
