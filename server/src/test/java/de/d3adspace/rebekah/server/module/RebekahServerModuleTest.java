package de.d3adspace.rebekah.server.module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import de.d3adspace.rebekah.commons.codec.PacketCodec;
import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import de.d3adspace.rebekah.server.RebekahServer;
import de.d3adspace.rebekah.server.kernel.Kernel;
import de.d3adspace.rebekah.server.transport.TransportServer;
import io.reactivex.netty.channel.ConnectionHandler;
import io.reactivex.netty.pipeline.PipelineConfigurator;
import io.reactivex.netty.server.RxServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;
import javax.inject.Provider;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Require dependency bindings.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class RebekahServerModuleTest {

    @Inject
    Provider<TransportServer> transportServerProvider;
    @Inject
    Provider<RebekahServer> rebekahServerProvider;
    @Inject
    Provider<RxServer<Request, Response>> rxServerProvider;
    @Inject
    Provider<ConnectionHandler<Request, Response>> connectionHandlerProvider;
    @Inject
    Provider<PipelineConfigurator<Request, Response>> pipelineConfiguratorProvider;
    @Inject
    Provider<Kernel> kernelProvider;
    @Inject
    Provider<PacketCodec> packetCodecProvider;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(BoundFieldModule.of(this), new RebekahServerModule());
        injector.injectMembers(this);
    }

    @Test
    void testProvideTransportServer() {
        TransportServer transportServer = transportServerProvider.get();

        assertNotNull(transportServer, "Transport server instance should not be null.");
    }

    @Test
    void testProvideRebekahServer() {
        RebekahServer rebekahServer = rebekahServerProvider.get();

        assertNotNull(rebekahServer, "Rebekah server instance should not be null.");
    }

    @Test
    void testProvideRxServer() {
        RxServer<Request, Response> rxServer = rxServerProvider.get();

        assertNotNull(rxServer, "Rx server instance should not be null.");
    }

    @Test
    void testProvideConnectionHandler() {
        ConnectionHandler<Request, Response> connectionHandler = connectionHandlerProvider.get();

        assertNotNull(connectionHandler, "Connection handler should not be null.");
    }

    @Test
    void testProvidePipelineConfigurator() {
        PipelineConfigurator<Request, Response> pipelineConfigurator = pipelineConfiguratorProvider.get();

        assertNotNull(pipelineConfigurator, "Pipeline configurator should not be null.");
    }

    @Test
    void testProvideKernel() {
        Kernel kernel = kernelProvider.get();

        assertNotNull(kernel, "Kernel should not be null.");
    }

    @Test
    void testProvidePacketCodec() {
        PacketCodec packetCodec = packetCodecProvider.get();


        assertNotNull(packetCodec, "Packet codec should not be null.");
    }
}