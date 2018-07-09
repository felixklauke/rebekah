package de.d3adspace.rebekah.client.provider;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.client.RxClient;
import io.reactivex.netty.pipeline.PipelineConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class RxClientProviderTest {

    private static final String TEST_SERVER_HOST = "localhost";
    private static final int TEST_SERVER_PORT = 8083;

    @Mock
    PipelineConfigurator<Response, Request> pipelineConfigurator;

    private RxClientProvider rxClientProvider;

    @BeforeEach
    void setUp() {
        rxClientProvider = new RxClientProvider(TEST_SERVER_HOST, TEST_SERVER_PORT, pipelineConfigurator);
    }

    @Test
    void testGet() {
        RxClient<Request, Response> rxClient = rxClientProvider.get();

        assertNotNull(rxClient, "Client should not be null.");
    }
}