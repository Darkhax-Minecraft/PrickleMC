package net.darkhax.pricklemc.common.impl.config.property;

import net.darkhax.pricklemc.common.api.config.property.IDefaultPropertyAdapters;
import net.darkhax.pricklemc.common.api.config.property.IPropertyAdapter;

import java.util.function.Consumer;

public class MinecraftPropertyPlugin implements IDefaultPropertyAdapters {

    @Override
    public void register(Consumer<IPropertyAdapter<?>> registry) {
        registry.accept(CodecProperty.RESOURCE_LOCATION);
        registry.accept(CodecProperty.BLOCK_POS);
        registry.accept(CodecProperty.TEXT_COMPONENT);
        registry.accept(CodecProperty.TEXT_STYLE);
        registry.accept(CodecProperty.EFFECT_INSTANCE);
        registry.accept(CodecProperty.ATTRIBUTE_MODIFIER);
        registry.accept(CodecProperty.ITEM_STACK);
        registry.accept(CodecProperty.INGREDIENT);
    }
}