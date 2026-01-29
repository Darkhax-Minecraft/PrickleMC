package net.darkhax.pricklemc.common.impl;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Constant variables used throughout the mod.
 */
public class Constants {

    /**
     * The ID of the mod.
     */
    public static final String MOD_ID = "prickle";

    /**
     * The display name of the mod.
     */
    public static final String MOD_NAME = "Prickle";

    /**
     * A logger instance that should only be used by the mod.
     */
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    /**
     * The default indent for JSON writers when writing JSON data. This is used to set and restore the indent value for
     * config files.
     */
    public static final String DEFAULT_INDENT = "  ";

    /**
     * Creates a new Identifier using the project namespace.
     *
     * @param path The path of the id.
     * @return A new Identifier for the given path.
     */
    public static Identifier id(String path) {
        return Identifier.tryBuild(MOD_ID, path);
    }
}