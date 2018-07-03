package de.d3adspace.rebekah.commons.context;

import de.d3adspace.rebekah.commons.request.Request;

/**
 * Abstraction of a request context that will hold a reference to a request by using the field {@link #request}. The
 * request can be obtained by using {@link #getRequest()}.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public abstract class AbstractRequestContext<RequestType extends Request> implements RequestContext {

    /**
     * The reference to the request handled in this context.
     */
    private final RequestType request;

    /**
     * Create a new request context by its underlying request.
     *
     * @param request The request.
     */
    public AbstractRequestContext(RequestType request) {
        this.request = request;
    }

    /**
     * Get the underlying request.
     *
     * @return The request.
     */
    RequestType getRequest() {
        return request;
    }
}
