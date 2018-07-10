package de.d3adspace.rebekah.commons.module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.commons.packet.factory.PacketFactory;
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
class CommonsModuleTest {

    @Inject
    Provider<PacketRegistry> packetRegistryProvider;
    @Inject
    Provider<IncomingMessageHandlerManager> packetHandlerManagerProvider;
    @Inject
    Provider<PacketFactory> packetFactoryProvider;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new CommonsModule(), BoundFieldModule.of(this));
        injector.injectMembers(this);
    }

    @Test
    void testProvidePacketRegistry() {
        PacketRegistry packetRegistry = packetRegistryProvider.get();

        assertNotNull(packetRegistry, "Packet registry should not be null");
    }

    @Test
    void testProvidePacketHandlerManager() {
        IncomingMessageHandlerManager incomingMessageHandlerManager = packetHandlerManagerProvider.get();

        assertNotNull(incomingMessageHandlerManager, "Packet handler manager should not be null.");
    }

    @Test
    void testProvidePacketFactory() {
        PacketFactory packetFactory = packetFactoryProvider.get();

        assertNotNull(packetFactory, "Packet factory should not be null.");
    }
}