package net.darkhax.pricklemc.fabric;

import net.darkhax.pricklemc.common.impl.PrickleMod;
import net.fabricmc.api.ModInitializer;
import net.minecraft.DetectedVersion;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class PrickleMCFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        PrickleMod.LOG.debug("Initializing Prickle.");
        CompletableFuture.runAsync(PrickleMCFabric::checkForUpdates);
    }

    private static void checkForUpdates() {
        try {
            final HttpURLConnection connection = (HttpURLConnection) URI.create("https://updates.blamejared.com/get?n=" + PrickleMod.MOD_ID + "&gv=" + DetectedVersion.tryDetectVersion().name() + "&ml=fabric").toURL().openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                PrickleMod.LOG.warn("Version checker is not available. Response code: {}", responseCode);
            }
            connection.disconnect();
        }
        catch (Exception e) {
            PrickleMod.LOG.warn("Version checker is not available.", e);
        }
    }
}