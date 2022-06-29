package com.prototype.aggregationservice.service.impl;

import com.prototype.aggregationservice.data.VideoCamera;
import com.prototype.aggregationservice.data.VideoCameraData;
import com.prototype.aggregationservice.helper.GsonHelper;
import com.prototype.aggregationservice.service.AggregationService;
import com.prototype.aggregationservice.utils.web.WebRequest;
import com.prototype.aggregationservice.utils.web.WebUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AggregationServiceImpl implements AggregationService {

    @Value(value = "${url}")
    private String url;

    private final GsonHelper gsonHelper;

    @Autowired
    public AggregationServiceImpl(GsonHelper gsonHelper) {
        this.gsonHelper = gsonHelper;
    }

    public List<VideoCamera> getVideoCameras() {
        //List<VideoCameraData> videoCameraData = loadVideoCamerasData(this.url);
        return List.of();
    }

    public List<VideoCameraData> loadVideoCamerasData(String url) throws IOException {
        HttpResponse response = WebRequest.create()
                .method(WebRequest.Method.GET)
                .url(url)
                .execute();

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new IOException("HTTP response code: " + response.getStatusLine().getStatusCode());
        }

        String json = WebUtils.toString(response.getEntity().getContent());
        if (json == null || json.isBlank()) {
            json = "{}";
        }
        return this.gsonHelper.getGson().fromJson(json, GsonHelper.TYPE_LIST_VIDEO_CAMERA_DATA);
    }

}
