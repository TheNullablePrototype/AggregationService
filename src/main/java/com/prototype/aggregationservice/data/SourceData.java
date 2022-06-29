package com.prototype.aggregationservice.data;

public final class SourceData {

    private final URLType urlType;
    private final String videoUrl;

    public SourceData(URLType urlType, String videoUrl) {
        this.urlType = urlType;
        this.videoUrl = videoUrl;
    }

    public URLType getUrlType() {
        return urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

}
