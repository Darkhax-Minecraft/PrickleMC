package net.darkhax.pricklemc.neoforge.impl.util;

import net.darkhax.pricklemc.common.api.util.IPlatformHelper;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public String getName() {
        return "NeoForge";
    }
}