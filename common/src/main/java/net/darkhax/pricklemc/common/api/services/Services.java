package net.darkhax.pricklemc.common.api.services;

import net.darkhax.pricklemc.common.api.util.IPlatformHelper;
import net.darkhax.pricklemc.common.impl.PrickleMod;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        PrickleMod.LOG.debug("Loaded {} for service {}.", loadedService, clazz);
        return loadedService;
    }

    public static <T> List<T> loadMany(Class<T> clazz) {
        final List<T> entries = ServiceLoader.load(clazz).stream().map(ServiceLoader.Provider::get).toList();
        PrickleMod.LOG.debug("Loaded {} entries for {}. {}", entries.size(), clazz, entries.stream().map(entry -> entry.getClass().getCanonicalName()).collect(Collectors.joining()));
        return entries;
    }
}