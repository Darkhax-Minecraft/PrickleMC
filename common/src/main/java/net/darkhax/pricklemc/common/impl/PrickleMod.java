package net.darkhax.pricklemc.common.impl;

import net.darkhax.pricklemc.common.api.services.Services;

public class PrickleMod {

    private static PrickleMod instance;
    private boolean hasInitialized = false;

    public void init() {
        if (hasInitialized) {
            throw new IllegalStateException("The " + Constants.MOD_NAME + " has already been initialized.");
        }
        this.runStartupChecks();
        hasInitialized = true;
    }

    private void runStartupChecks() {
        if (Services.PLATFORM == null) {
            throw new IllegalStateException("Services are not available.");
        }
    }

    /**
     * Gets the mod instance. If an instance does not exist it will be created.
     *
     * @return The mod instance.
     */
    public static PrickleMod getInstance() {
        if (instance == null) {
            instance = new PrickleMod();
        }
        return instance;
    }
}