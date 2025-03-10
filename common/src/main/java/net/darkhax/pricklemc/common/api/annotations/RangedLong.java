package net.darkhax.pricklemc.common.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When this annotation is used on a long property it will validate that the value is within the range, inclusive of the
 * minimum and maximum values.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RangedLong {

    /**
     * Gets the lowest value permitted for the property.
     *
     * @return The lowest permitted value.
     */
    long min() default Long.MIN_VALUE;

    /**
     * Gets the highest value permitted for the property.
     *
     * @return The highest permitted value.
     */
    long max() default Long.MAX_VALUE;
}