package minicraft.util;

import java.io.Reader;
import java.io.StringReader;

import org.jetbrains.annotations.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

public class JsonUtil {
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static boolean isNumber(JsonElement obj) {
        return obj.isJsonPrimitive() && obj.getAsJsonPrimitive().isNumber();
    }

    public static boolean isString(JsonElement obj) {
        return obj.isJsonPrimitive() && obj.getAsJsonPrimitive().isString();
    }

    public static boolean isBoolean(JsonElement obj) {
        return obj.isJsonPrimitive() && obj.getAsJsonPrimitive().isBoolean();
    }

    public static boolean hasElement(JsonObject obj, String key) {
        return obj.has(key) && !obj.get(key).isJsonNull();
    }

    public static boolean hasNumber(JsonObject obj, String key) {
        return obj.has(key) && obj.get(key).isJsonPrimitive() && obj.getAsJsonPrimitive(key).isNumber();
    }

    public static boolean hasBoolean(JsonObject obj, String key) {
        return obj.has(key) && obj.get(key).isJsonPrimitive() && obj.getAsJsonPrimitive(key).isBoolean();
    }

    public static boolean hasString(JsonObject obj, String key) {
        return obj.has(key) && obj.get(key).isJsonPrimitive() && obj.getAsJsonPrimitive(key).isString();
    }

    public static boolean hasObject(JsonObject obj, String key) {
        return obj.has(key) && obj.get(key).isJsonObject();
    }

    public static boolean hasArray(JsonObject obj, String key) {
        return obj.has(key) && obj.get(key).isJsonArray();
    }

    @Nullable
    public static byte getByte(JsonObject obj, String key) {
        return hasNumber(obj, key) ? obj.get(key).getAsByte() : null;
    }

    public static byte getByte(JsonObject obj, String key, byte defaultValue) {
        return hasNumber(obj, key) ? obj.get(key).getAsByte() : defaultValue;
    }

    @Nullable
    public static short getShort(JsonObject obj, String key) {
        return hasNumber(obj, key) ? obj.get(key).getAsShort() : null;
    }

    public static short getShort(JsonObject obj, String key, short defaultValue) {
        return hasNumber(obj, key) ? obj.get(key).getAsShort() : defaultValue;
    }

    @Nullable
    public static int getInt(JsonObject obj, String key) {
        return hasNumber(obj, key) ? obj.get(key).getAsInt() : null;
    }

    public static int getInt(JsonObject obj, String key, int defaultValue) {
        return hasNumber(obj, key) ? obj.get(key).getAsInt() : defaultValue;
    }

    @Nullable
    public static long getLong(JsonObject obj, String key) {
        return hasNumber(obj, key) ? obj.get(key).getAsLong() : null;
    }

    public static long getLong(JsonObject obj, String key, long defaultValue) {
        return hasNumber(obj, key) ? obj.get(key).getAsLong() : defaultValue;
    }

    @Nullable
    public static String getString(JsonObject obj, String key) {
        return hasString(obj, key) ? obj.get(key).getAsString() : null;
    }

    public static String getString(JsonObject obj, String key, String defaultValue) {
        return hasString(obj, key) ? obj.get(key).getAsString() : defaultValue;
    }

    @Nullable
    public static boolean getBoolean(JsonObject obj, String key) {
        return hasBoolean(obj, key) ? obj.get(key).getAsBoolean() : null;
    }

    public static boolean getBoolean(JsonObject obj, String key, boolean defaultValue) {
        return hasBoolean(obj, key) ? obj.get(key).getAsBoolean() : defaultValue;
    }

    @Nullable
    public static JsonObject getObject(JsonObject obj, String key) {
        return hasObject(obj, key) ? obj.getAsJsonObject(key) : null;
    }

    public static JsonObject getObject(JsonObject obj, String key, JsonObject defaultValue) {
        return hasObject(obj, key) ? obj.getAsJsonObject(key) : defaultValue;
    }

    @Nullable
    public static JsonArray getArray(JsonObject obj, String key) {
        return hasArray(obj, key) ? obj.getAsJsonArray(key) : null;
    }

    public static JsonArray getArray(JsonObject obj, String key, JsonArray defaultValue) {
        return hasArray(obj, key) ? obj.getAsJsonArray(key) : defaultValue;
    }

    public static <T> T deserialize(Reader reader, Class<T> clazz, boolean lenient) {
        try {
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(lenient);
            return GSON.getAdapter(clazz).read(jsonReader);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    public static <T> JsonObject deserialize(Reader reader, boolean lenient) {
        return deserialize(reader, JsonObject.class, lenient);
    }

    public static <T> JsonObject deserialize(Reader reader) {
        return deserialize(reader, JsonObject.class, false);
    }

    public static <T> T deserialize(String data, Class<T> clazz, boolean lenient) {
        return deserialize(new StringReader(data), clazz, lenient);
    }

    public static <T> JsonObject deserialize(String data, boolean lenient) {
        return deserialize(new StringReader(data), JsonObject.class, lenient);
    }

    public static <T> JsonObject deserialize(String data) {
        return deserialize(new StringReader(data), JsonObject.class, false);
    }

    public static String toString(JsonElement obj) {
        return GSON.toJson(obj);
    }
}
