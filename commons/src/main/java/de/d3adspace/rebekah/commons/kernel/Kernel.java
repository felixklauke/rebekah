package de.d3adspace.rebekah.commons.kernel;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface Kernel {

  /**
   * Handle the given message in the given context.
   *
   * @param messageContext The context of the message.
   * @param message        The request.
   */
  void handleMessage(MessageContext messageContext, IncomingMessage message);
}
