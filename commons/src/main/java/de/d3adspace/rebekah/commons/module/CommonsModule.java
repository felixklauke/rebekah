package de.d3adspace.rebekah.commons.module;

import com.google.inject.AbstractModule;
import de.d3adspace.rebekah.commons.packet.PacketRegistry;
import de.d3adspace.rebekah.commons.packet.PacketRegistryImpl;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class CommonsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PacketRegistry.class).to(PacketRegistryImpl.class).asEagerSingleton();
    }
}
