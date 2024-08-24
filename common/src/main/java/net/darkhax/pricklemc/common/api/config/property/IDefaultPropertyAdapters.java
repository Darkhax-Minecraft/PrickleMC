package net.darkhax.pricklemc.common.api.config.property;

import java.util.function.Consumer;

/**
 * Allows additional default property adapters to be defined. Mods should only use this to define adapters for their own
 * types.
 */
public interface IDefaultPropertyAdapters {

    /**
     * Registers a new default property adapter. Mods should only define properties for their own types!
     *
     * @param registry Register a new default adapter by passing it into the consumer.
     */
    void register(Consumer<IPropertyAdapter<?>> registry);
}