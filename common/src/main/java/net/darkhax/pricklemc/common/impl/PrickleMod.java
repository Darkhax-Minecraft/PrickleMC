package net.darkhax.pricklemc.common.impl;

import net.darkhax.pricklemc.common.api.services.Services;
import net.darkhax.pricklemc.common.api.util.IPlatformHelper;
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

    /**
     * Platform specific context and utilities.
     */
    public static final IPlatformHelper PLATFORM = Services.load(IPlatformHelper.class);
}