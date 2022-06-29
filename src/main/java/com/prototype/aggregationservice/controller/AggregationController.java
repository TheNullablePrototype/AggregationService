package com.prototype.aggregationservice.controller;

import com.prototype.aggregationservice.data.VideoCamera;
import com.prototype.aggregationservice.helper.GsonHelper;
import com.prototype.aggregationservice.service.VideoCamerasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aggregation")
public class AggregationController {

    @Value(value = "${url}")
    private String url;

    private final VideoCamerasService videoCamerasService;
    private final GsonHelper gsonHelper;

    @Autowired
    public AggregationController(VideoCamerasService videoCamerasService, GsonHelper gsonHelper) {
        this.videoCamerasService = videoCamerasService;
        this.gsonHelper = gsonHelper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAggregations() {
        List<VideoCamera> cameras = this.videoCamerasService.getVideoCameras(this.url);
        if (cameras.isEmpty())
            return "{}";
        return this.gsonHelper.getGson().toJson(cameras, GsonHelper.TYPE_LIST_VIDEO_CAMERA);
    }

}
