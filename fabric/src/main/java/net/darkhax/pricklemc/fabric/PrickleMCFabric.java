package net.darkhax.pricklemc.fabric;

import net.darkhax.pricklemc.common.impl.Constants;
import net.darkhax.pricklemc.common.impl.PrickleMod;
import net.fabricmc.api.ModInitializer;
import net.minecraft.DetectedVersion;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class PrickleMCFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        PrickleMod.getInstance().init();
        CompletableFuture.runAsync(PrickleMCFabric::checkForUpdates);
    }

    private static void checkForUpdates() {
        try {
            final HttpURLConnection connection = (HttpURLConnection) URI.create("https://updates.blamejared.com/get?n=" + Constants.MOD_ID + "&gv=" + DetectedVersion.tryDetectVersion().name() + "&ml=fabric").toURL().openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                Constants.LOG.warn("Version checker is not available. Response code: {}", responseCode);
            }
        }
        catch (Exception e) {
            Constants.LOG.warn("Version checker is not available.", e);
        }
    }
}