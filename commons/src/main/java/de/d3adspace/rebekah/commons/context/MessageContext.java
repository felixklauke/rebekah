package de.d3adspace.rebekah.commons.context;

import de.d3adspace.rebekah.commons.message.OutgoingMessage;

/**
 * Describes the context of a request, that can be answered by a response.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface MessageContext {

    /**
     * Resume the request response lifecycle with the given response.
     *
     * @param outgoingMessage The outgoing message.
     */
    void resume(OutgoingMessage outgoingMessage);
}
