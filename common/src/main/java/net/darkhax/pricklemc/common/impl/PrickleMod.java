package net.darkhax.pricklemc.common.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrickleMod {

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
}