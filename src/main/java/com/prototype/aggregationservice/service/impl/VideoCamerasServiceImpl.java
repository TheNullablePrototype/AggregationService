package com.prototype.aggregationservice.service.impl;

import com.prototype.aggregationservice.data.SourceData;
import com.prototype.aggregationservice.data.TokenData;
import com.prototype.aggregationservice.data.VideoCamera;
import com.prototype.aggregationservice.data.VideoCameraData;
import com.prototype.aggregationservice.helper.FutureHelper;
import com.prototype.aggregationservice.helper.GsonHelper;
import com.prototype.aggregationservice.helper.WebHelper;
import com.prototype.aggregationservice.service.HttpService;
import com.prototype.aggregationservice.service.VideoCamerasService;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class VideoCamerasServiceImpl implements VideoCamerasService {

    private final HttpService httpService;
    private final GsonHelper gsonHelper;

    @Autowired
    public VideoCamerasServiceImpl(HttpService httpService, GsonHelper gsonHelper) {
        this.httpService = httpService;
        this.gsonHelper = gsonHelper;
    }

    @Override
    public List<VideoCamera> getVideoCameras(String url) {
        try {

            SimpleHttpResponse response = httpService.createFutureResponse(WebHelper.createRequest(url)).get();

            List<VideoCameraData> dataList = this.gsonHelper.getGson()
                    .fromJson(response.getBody().getBodyText(), GsonHelper.TYPE_LIST_VIDEO_CAMERA_DATA);

            return dataList.parallelStream().unordered()
                    .map(data -> {
                        try {

                            final Future<SimpleHttpResponse> sourceFuture =
                                    httpService.createFutureResponse(WebHelper.createRequest(data.sourceDataUrl()));
                            final Future<SimpleHttpResponse> tokenFuture =
                                    httpService.createFutureResponse(WebHelper.createRequest(data.tokenDataUrl()));

                            CompletableFuture.allOf(
                                    FutureHelper.fromFuture(sourceFuture),
                                    FutureHelper.fromFuture(tokenFuture)
                            ).get();

                            final SourceData sourceData = this.gsonHelper.getGson()
                                    .fromJson(sourceFuture.get().getBodyText(), SourceData.class);

                            final TokenData tokenData = this.gsonHelper.getGson()
                                    .fromJson(tokenFuture.get().getBodyText(), TokenData.class);

                            return new VideoCamera(data.id(), sourceData, tokenData);

                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
