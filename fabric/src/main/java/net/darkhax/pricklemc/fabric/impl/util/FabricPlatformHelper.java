package net.darkhax.pricklemc.fabric.impl.util;

import net.darkhax.pricklemc.common.api.util.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public String getName() {
        return "Fabric";
    }
}