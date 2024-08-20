package net.darkhax.pricklemc.fabric.impl;

import net.darkhax.pricklemc.common.impl.PrickleMod;
import net.fabricmc.api.ModInitializer;

public class FabricMod implements ModInitializer {

    @Override
    public void onInitialize() {
        PrickleMod.getInstance().init();
    }
}