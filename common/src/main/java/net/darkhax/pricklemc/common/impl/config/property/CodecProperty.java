package net.darkhax.pricklemc.common.impl.config.property;

import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.darkhax.pricklemc.common.api.annotations.Value;
import net.darkhax.pricklemc.common.api.config.PropertyResolver;
import net.darkhax.pricklemc.common.api.config.comment.IComment;
import net.darkhax.pricklemc.common.api.config.property.IPropertyAdapter;
import net.darkhax.pricklemc.common.api.config.property.ObjectProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class CodecProperty<T> extends ObjectProperty<T> {

    public static final Adapter<ResourceLocation> RESOURCE_LOCATION = of(ResourceLocation.class, ResourceLocation.CODEC);
    public static final Adapter<BlockPos> BLOCK_POS = of(BlockPos.class, BlockPos.CODEC);
    public static final Adapter<Component> TEXT_COMPONENT = of(Component.class, ComponentSerialization.CODEC);
    public static final Adapter<Style> TEXT_STYLE = of(Style.class, Style.Serializer.CODEC);
    public static final Adapter<MobEffectInstance> EFFECT_INSTANCE = of(MobEffectInstance.class, MobEffectInstance.CODEC);
    public static final Adapter<AttributeModifier> ATTRIBUTE_MODIFIER = of(AttributeModifier.class, AttributeModifier.CODEC);
    public static final Adapter<ItemStack> ITEM_STACK = of(ItemStack.class, ItemStack.CODEC);
    public static final Adapter<Ingredient> INGREDIENT = of(Ingredient.class, Ingredient.CODEC);

    private final Codec<T> codec;

    public CodecProperty(Field field, Object parent, T defaultValue, Value valueMeta, IComment comment, Codec<T> codec) {
        super(field, parent, defaultValue, valueMeta, comment);
        this.codec = codec;
    }

    @Override
    public void writeValue(T value, JsonWriter writer, PropertyResolver resolver, Logger logger) throws IOException {
        final JsonElement json = this.codec.encodeStart(JsonOps.INSTANCE, value).getOrThrow();
        Streams.write(json, writer);
    }

    @Override
    public T readValue(JsonReader reader, PropertyResolver resolver, Logger logger) throws IOException {
        final JsonElement json = Streams.parse(reader);
        return this.codec.decode(JsonOps.INSTANCE, json).getOrThrow().getFirst();
    }

    public static <T> Adapter<T> of(Type classType, Codec<T> codec) {
        return new Adapter<>(classType, codec);
    }

    public static class Adapter<T> implements IPropertyAdapter<CodecProperty<T>> {

        private final Type type;
        private final Codec<T> codec;

        private Adapter(Type type, Codec<T> codec) {
            this.type = type;
            this.codec = codec;
        }

        @Override
        public @Nullable CodecProperty<T> toValue(PropertyResolver resolver, Field field, Object parent, @Nullable Object value, Value valueMeta) throws IOException {
            if (field.getGenericType().equals(this.type)) {
                return new CodecProperty<>(field, parent, (T) value, valueMeta, resolver.toComment(field, value, valueMeta), codec);
            }
            return null;
        }
    }
}