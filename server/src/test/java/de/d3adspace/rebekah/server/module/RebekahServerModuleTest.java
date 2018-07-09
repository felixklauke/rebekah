package de.d3adspace.rebekah.server.module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import de.d3adspace.rebekah.commons.codec.PacketCodec;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
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
    Provider<RxServer<IncomingMessage, OutgoingMessage>> rxServerProvider;
    @Inject
    Provider<ConnectionHandler<IncomingMessage, OutgoingMessage>> connectionHandlerProvider;
    @Inject
    Provider<PipelineConfigurator<IncomingMessage, OutgoingMessage>> pipelineConfiguratorProvider;
    @Inject
    Provider<Kernel> kernelProvider;
    @Inject
    Provider<PacketCodec> packetCodecProvider;
    @Inject
    Provider<IncomingMessageHandlerManager> packetHandlerManagerProvider;
    @Inject
    Provider<PacketRegistry> packetRegistryProvider;
    @Inject
    Provider<IncomingMessageHandlerManager> requestHandlerManagerProvider;

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
        RxServer<IncomingMessage, OutgoingMessage> rxServer = rxServerProvider.get();

        assertNotNull(rxServer, "Rx server instance should not be null.");
    }

    @Test
    void testProvideConnectionHandler() {
        ConnectionHandler<IncomingMessage, OutgoingMessage> connectionHandler = connectionHandlerProvider.get();

        assertNotNull(connectionHandler, "Connection handler should not be null.");
    }

    @Test
    void testProvidePipelineConfigurator() {
        PipelineConfigurator<IncomingMessage, OutgoingMessage> pipelineConfigurator = pipelineConfiguratorProvider.get();

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

    @Test
    void testProvidePacketHandlerManager() {
        IncomingMessageHandlerManager incomingMessageHandlerManager = packetHandlerManagerProvider.get();

        assertNotNull(incomingMessageHandlerManager, "Packet handler manager should not be null.");
    }

    @Test
    void testProvidePacketRegistry() {
        PacketRegistry packetRegistry = packetRegistryProvider.get();

        assertNotNull(packetRegistry, "Packet registry should not be null.");
    }

    @Test
    void testProvideRequestHandlerManager() {
        IncomingMessageHandlerManager incomingMessageHandlerManager = requestHandlerManagerProvider.get();

        assertNotNull(incomingMessageHandlerManager, "Request handler manager should not be null.");
    }
}