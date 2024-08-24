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

/**
 * A config property where the value is serialized using a Minecraft Codec.
 *
 * @param <T> The type of value serialized by the codec.
 */
public class CodecProperty<T> extends ObjectProperty<T> {

    /**
     * Builtin support for resource locations using Mojang's codec.
     */
    public static final Adapter<ResourceLocation> RESOURCE_LOCATION = of(ResourceLocation.class, ResourceLocation.CODEC);

    /**
     * Builtin support for block positions using Mojang's codec.
     */
    public static final Adapter<BlockPos> BLOCK_POS = of(BlockPos.class, BlockPos.CODEC);

    /**
     * Builtin support for text components using Mojang's codec.
     */
    public static final Adapter<Component> TEXT_COMPONENT = of(Component.class, ComponentSerialization.CODEC);

    /**
     * Builtin support for text styles using Mojang's codec.
     */
    public static final Adapter<Style> TEXT_STYLE = of(Style.class, Style.Serializer.CODEC);

    /**
     * Builtin support for status effects using Mojang's codec.
     */
    public static final Adapter<MobEffectInstance> EFFECT_INSTANCE = of(MobEffectInstance.class, MobEffectInstance.CODEC);

    /**
     * Builtin support for attribute modifiers using Mojang's codec.
     */
    public static final Adapter<AttributeModifier> ATTRIBUTE_MODIFIER = of(AttributeModifier.class, AttributeModifier.CODEC);

    /**
     * Builtin support for item stacks using Mojang's codec.
     */
    public static final Adapter<ItemStack> ITEM_STACK = of(ItemStack.class, ItemStack.CODEC);

    /**
     * Builtin support for ingredients using Mojang's codec.
     */
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

    /**
     * Creates an adapter that will process a given type using a codec.
     *
     * @param classType The type to serialize using the codec.
     * @param codec     The codec to serialize the value.
     * @param <T>       The type being adapted.
     * @return An adapter that uses a codec to serialize data.
     */
    public static <T> Adapter<T> of(Type classType, Codec<T> codec) {
        return new Adapter<>(classType, codec);
    }

    /**
     * A type adapter that will use a Codec to serialize a given type.
     *
     * @param <T> The type to adapt and serialize.
     */
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