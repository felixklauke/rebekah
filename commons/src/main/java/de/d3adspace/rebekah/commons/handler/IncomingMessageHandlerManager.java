package de.d3adspace.rebekah.commons.handler;

import de.d3adspace.rebekah.commons.context.MessageContext;
import de.d3adspace.rebekah.commons.message.IncomingMessage;

/**
 * Manages all known message handlers.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface IncomingMessageHandlerManager {

    /**
     * Register the given message handler.
     *
     * @param incomingMessageHandler The packet handler.
     */
    void registerMessageHandler(IncomingMessageHandler incomingMessageHandler);

    /**
     * Unregister the given message handler.
     *
     * @param incomingMessageHandler The message handler.
     */
    void unregisterMessageHandler(IncomingMessageHandler incomingMessageHandler);

    /**
     * Check if the given message handler is already registered.
     *
     * @param incomingMessageHandler The message handler.
     *
     * @return If the given message handler is already registered.
     */
    boolean isMessageHandlerRegistered(IncomingMessageHandler incomingMessageHandler);

    /**
     * Process the given message in the given message by let it be handled by all eligible handlers.
     *
     * @param messageContext The context of the message.
     * @param message        The message.
     */
    void process(MessageContext messageContext, IncomingMessage message);
}
