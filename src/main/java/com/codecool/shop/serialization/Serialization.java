package com.codecool.shop.serialization;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Serialization<T> {

    public String serialization(List<T> listToSerialize) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(listToSerialize);
    }

    public String serialization(T object) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(object);
    }


    public List<T> deSerialization(String jsonArray) {
        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();
        return new Gson().fromJson(jsonArray, listType);
    }

}
