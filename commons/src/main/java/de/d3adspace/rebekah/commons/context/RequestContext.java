package de.d3adspace.rebekah.commons.context;

import de.d3adspace.rebekah.commons.response.Response;

/**
 * Describes the context of a request, that can be answered by a response.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface RequestContext {

    /**
     * Resume the request response lifecycle with the given response.
     *
     * @param response The response.
     */
    void resume(Response response);
}
