package de.d3adspace.rebekah.commons.module;

import com.google.inject.AbstractModule;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManager;
import de.d3adspace.rebekah.commons.handler.IncomingMessageHandlerManagerImpl;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.commons.packet.PacketRegistryImpl;
import de.d3adspace.rebekah.commons.packet.factory.GuicePacketFactory;
import de.d3adspace.rebekah.commons.packet.factory.PacketFactory;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class CommonsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PacketRegistry.class).to(PacketRegistryImpl.class).asEagerSingleton();
        bind(IncomingMessageHandlerManager.class).to(IncomingMessageHandlerManagerImpl.class).asEagerSingleton();

        bind(PacketFactory.class).to(GuicePacketFactory.class);
    }
}
