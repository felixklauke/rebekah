package de.d3adspace.rebekah.client.module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import de.d3adspace.rebekah.client.RebekahClient;
import de.d3adspace.rebekah.client.transport.TransportClient;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import io.reactivex.netty.client.RxClient;
import io.reactivex.netty.pipeline.PipelineConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;
import javax.inject.Provider;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class ClientModuleTest {

    @Inject
    Provider<RebekahClient> rebekahClientProvider;
    @Inject
    Provider<TransportClient> transportClientProvider;
    @Inject
    Provider<PacketRegistry> packetRegistryProvider;
    @Inject
    Provider<RxClient<OutgoingMessage, IncomingMessage>> rxClientProvider;
    @Inject
    Provider<PipelineConfigurator<IncomingMessage, OutgoingMessage>> pipelineConfiguratorProvider;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(BoundFieldModule.of(this), new ClientModule());
        injector.injectMembers(this);
    }

    @Test
    void testProvideRebekahClient() {
        RebekahClient rebekahClient = rebekahClientProvider.get();

        assertNotNull(rebekahClient, "Client should not be null.");
    }

    @Test
    void testProvideTransportClient() {
        TransportClient transportClient = transportClientProvider.get();

        assertNotNull(transportClient, "Transport client should not be null.");
    }

    @Test
    void testProvidePacketRegistry() {
        PacketRegistry packetRegistry = packetRegistryProvider.get();

        assertNotNull(packetRegistry, "Packet registry should not be null.");
    }

    @Test
    void testProvideRxClient() {
        RxClient<OutgoingMessage, IncomingMessage> requestResponseRxClient = rxClientProvider.get();

        assertNotNull(requestResponseRxClient, "Rx Client should not be null.");
    }

    @Test
    void testProvidePipelineConfigurator() {
        PipelineConfigurator<IncomingMessage, OutgoingMessage> requestResponsePipelineConfigurator = pipelineConfiguratorProvider.get();

        assertNotNull(requestResponsePipelineConfigurator, "Pipeline configurator should not be null.");
    }
}