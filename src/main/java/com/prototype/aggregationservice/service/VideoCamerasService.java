package com.prototype.aggregationservice.service;

import com.prototype.aggregationservice.data.VideoCamera;

import java.util.List;

public interface VideoCamerasService {

    List<VideoCamera> getVideoCameras(String url);

}
