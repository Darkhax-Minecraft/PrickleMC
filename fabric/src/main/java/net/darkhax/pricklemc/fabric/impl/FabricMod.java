package net.darkhax.pricklemc.fabric.impl;

import net.darkhax.pricklemc.common.impl.Constants;
import net.darkhax.pricklemc.common.impl.PrickleMod;
import net.fabricmc.api.ModInitializer;
import net.minecraft.DetectedVersion;

import java.net.HttpURLConnection;
import java.net.URI;

public class FabricMod implements ModInitializer {

    @Override
    public void onInitialize() {
        PrickleMod.getInstance().init();
        checkForUpdates();
    }

    private static void checkForUpdates() {
        try {
            final HttpURLConnection connection = (HttpURLConnection) new URI("https://updates.blamejared.com/get?n=" + Constants.MOD_ID + "&gv=" + DetectedVersion.BUILT_IN.name() + "&ml=fabric").toURL().openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                Constants.LOG.warn("Version checker is not available.");
            }
        }
        catch (Exception e) {
        }
    }
}