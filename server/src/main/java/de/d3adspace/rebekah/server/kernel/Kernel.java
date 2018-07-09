package de.d3adspace.rebekah.server.kernel;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface Kernel {

    /**
     * Handle the given request in the given context.
     *
     * @param messageContext The context of the request.
     * @param request        The request.
     */
    void handleRequest(MessageContext messageContext, IncomingMessage request);
}
