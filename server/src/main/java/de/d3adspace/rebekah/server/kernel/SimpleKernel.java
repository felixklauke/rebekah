package de.d3adspace.rebekah.server.kernel;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.handler.RequestHandlerManager;
import de.d3adspace.rebekah.commons.request.Request;

import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class SimpleKernel implements Kernel {

    /**
     * The packet handler manager with the overview over all packet handlers.
     */
    private final RequestHandlerManager requestHandlerManager;

    /**
     * Create a new kernel instance by its underlying packet handler manager.
     *
     * @param requestHandlerManager The packet handler manager.
     */
    @Inject
    public SimpleKernel(RequestHandlerManager requestHandlerManager) {
        this.requestHandlerManager = requestHandlerManager;
    }

    @Override
    public void handleRequest(RequestContext requestContext, Request request) {
        requestHandlerManager.process(requestContext, request);
    }
}
