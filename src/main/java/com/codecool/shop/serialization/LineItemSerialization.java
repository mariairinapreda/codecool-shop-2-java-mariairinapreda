package com.codecool.shop.serialization;

import com.codecool.shop.model.LineItem;
import com.google.gson.*;

import javax.sound.sampled.Line;
import java.lang.reflect.Type;

public class LineItemSerialization implements JsonSerializer<LineItem>, JsonDeserializer<LineItem> {
    @Override
    public LineItem deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(LineItem item, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
