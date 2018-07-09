package de.d3adspace.rebekah.server.kernel;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.message.IncomingMessage;

import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class SimpleKernel implements Kernel {

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
    public SimpleKernel(IncomingMessageHandlerManager incomingMessageHandlerManager) {
        this.incomingMessageHandlerManager = incomingMessageHandlerManager;
    }

    @Override
    public void handleRequest(RequestContext requestContext, IncomingMessage request) {
        incomingMessageHandlerManager.process(requestContext, request);
    }
}
