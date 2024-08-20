package net.darkhax.pricklemc.common.impl;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

    public static final String MOD_ID = "prickle";
    public static final String MOD_NAME = "Prickle";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    /**
     * The default indent for JSON writers when writing JSON data. This is used to set and restore the indent value for
     * config files.
     */
    public static final String DEFAULT_INDENT = "  ";

    public static ResourceLocation id(String path) {
        return ResourceLocation.tryBuild(MOD_ID, path);
    }
}