package de.d3adspace.rebekah.commons.packet.description.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PacketMeta {

    /**
     * The unique id the packet.
     *
     * @return The id.
     */
    int id();

    /**
     * Custom name of the packet or empty.
     *
     * @return The name.
     */
    String name() default "";
}
