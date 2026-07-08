package net.darkhax.pricklemc.common.impl.config.property;

import net.darkhax.pricklemc.common.api.config.property.IDefaultPropertyAdapters;
import net.darkhax.pricklemc.common.api.config.property.IPropertyAdapter;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviders;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.LevelBasedValue;

import java.util.function.Consumer;

public class MinecraftPropertyPlugin implements IDefaultPropertyAdapters {

    @Override
    public void register(Consumer<IPropertyAdapter<?>> registry) {
        registry.accept(CodecProperty.of(Identifier.class, Identifier.CODEC));
        registry.accept(CodecProperty.of(BlockPos.class, BlockPos.CODEC));
        registry.accept(CodecProperty.of(Component.class, ComponentSerialization.CODEC));
        registry.accept(CodecProperty.of(Style.class, Style.Serializer.CODEC));
        registry.accept(CodecProperty.of(MobEffectInstance.class, MobEffectInstance.CODEC));
        registry.accept(CodecProperty.of(AttributeModifier.class, AttributeModifier.CODEC));
        registry.accept(CodecProperty.of(ItemStack.class, ItemStack.CODEC));
        registry.accept(CodecProperty.of(Ingredient.class, Ingredient.CODEC));
        registry.accept(CodecProperty.of(LevelBasedValue.class, LevelBasedValue.CODEC));
        registry.accept(CodecProperty.of(IntProvider.class, IntProviders.CODEC));
        registry.accept(CodecProperty.of(FloatProvider.class, FloatProviders.CODEC));
    }
}