package net.darkhax.pricklemc.common.api.config.comment;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * A GSON type adapter that handles reading and writing comments through GSON. This will serialize the values from
 * {@link IComment#getLines()} and deserialize values to the basic {@link Comment} that only holds the data.
 */
public final class CommentTypeAdapter implements JsonSerializer<IComment>, JsonDeserializer<IComment> {

    /**
     * The single instance of this type adapter.
     */
    public static final CommentTypeAdapter INSTANCE = new CommentTypeAdapter();

    private CommentTypeAdapter() {
    }

    @Override
    public IComment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json instanceof JsonPrimitive primitive && primitive.isString()) {
            return new Comment(primitive.getAsString());
        }
        else if (json instanceof JsonArray array) {
            final String[] comments = new String[array.size()];
            for (int i = 0; i < comments.length; i++) {
                if (array.get(i) instanceof JsonPrimitive primitive && primitive.isString()) {
                    comments[i] = primitive.getAsString();
                }
                else {
                    throw new JsonParseException("Comments can only contain strings. Found " + array.get(i));
                }
            }
            return new Comment(comments);
        }
        throw new JsonParseException("Comments can only be defined as a string or a string array. Found " + json);
    }

    @Override
    public JsonElement serialize(IComment src, Type typeOfSrc, JsonSerializationContext context) {
        if (src.getLines().length == 1) {
            return new JsonPrimitive(src.getLines()[0]);
        }
        else {
            final JsonArray array = new JsonArray();
            for (String line : src.getLines()) {
                array.add(line);
            }
            return array;
        }
    }
}
