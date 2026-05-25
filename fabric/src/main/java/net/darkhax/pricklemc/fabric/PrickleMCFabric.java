package net.darkhax.pricklemc.fabric;

import net.darkhax.pricklemc.common.impl.PrickleMod;
import net.fabricmc.api.ModInitializer;
import net.minecraft.DetectedVersion;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrickleMCFabric implements ModInitializer {

    private static final ExecutorService UPDATE_EXECUTOR = Executors.newSingleThreadExecutor(r -> {
        final Thread t = new Thread(r, "PrickleMC-Update-Checker");
        t.setDaemon(true);
        return t;
    });

    @Override
    public void onInitialize() {
        PrickleMod.LOG.debug("Initializing Prickle.");
        CompletableFuture.runAsync(PrickleMCFabric::checkForUpdates, UPDATE_EXECUTOR);
    }

    private static void checkForUpdates() {
        try {
            final HttpURLConnection connection = (HttpURLConnection) URI.create("https://updates.blamejared.com/get?n=" + PrickleMod.MOD_ID + "&gv=" + DetectedVersion.tryDetectVersion().name() + "&ml=fabric").toURL().openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
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