package com.prototype.aggregationservice.helper;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.prototype.aggregationservice.data.VideoCamera;
import com.prototype.aggregationservice.data.VideoCameraData;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class GsonHelper {

    public final static Type TYPE_LIST_VIDEO_CAMERA_DATA = new TypeToken<List<VideoCameraData>>() {}.getType();
    public final static Type TYPE_LIST_VIDEO_CAMERA = new TypeToken<List<VideoCamera>>() {}.getType();

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(VideoCamera.class, new VideoCameraGsonAdapter())
            .setPrettyPrinting()
            .create();

    public Gson getGson() {
        return this.gson;
    }

    public static class VideoCameraGsonAdapter implements JsonSerializer<VideoCamera> {

        @Override
        public JsonElement serialize(VideoCamera src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("id", src.id());
            object.addProperty("urlType", src.sourceData().getUrlType().name());
            object.addProperty("videoUrl", src.sourceData().getVideoUrl());
            object.addProperty("value", src.tokenData().getValue());
            object.addProperty("ttl", src.tokenData().getTtl());
            return object;
        }

    }

}
