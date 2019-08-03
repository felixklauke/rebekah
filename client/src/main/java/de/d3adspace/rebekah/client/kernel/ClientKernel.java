package de.d3adspace.rebekah.client.kernel;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.kernel.Kernel;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class ClientKernel implements Kernel {

  private IncomingMessageHandlerManager incomingMessageHandlerManager;

  @Inject
  public ClientKernel(IncomingMessageHandlerManager incomingMessageHandlerManager) {
    this.incomingMessageHandlerManager = incomingMessageHandlerManager;
  }

  @Override
  public void handleMessage(MessageContext messageContext, IncomingMessage message) {
    incomingMessageHandlerManager.process(messageContext, message);
  }
}
