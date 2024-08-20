package net.darkhax.pricklemc.common.api.util;

import java.io.File;
import java.nio.file.Path;

/**
 * The PlatformHelper provides useful context and information about the platform the game is running on.
 */
public interface IPlatformHelper {

    /**
     * Gets the specified configuration path for the game.
     *
     * @return The specified configuration path for the game.
     */
    Path getConfigPath();

    /**
     * Gets the specified configuration directory as a file reference.
     *
     * @return The specified configuration path for the game.
     */
    default File getConfigDirectory() {
        return this.getConfigPath().toFile();
    }

    /**
     * Gets the name of the platform.
     *
     * @return The name of the platform.
     */
    String getName();
}