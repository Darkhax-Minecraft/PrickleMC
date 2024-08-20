package net.darkhax.pricklemc.forge.impl.util;

import net.darkhax.pricklemc.common.api.util.IPlatformHelper;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public String getName() {
        return "Forge";
    }
}