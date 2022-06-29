package com.prototype.aggregationservice.data;

import java.util.Objects;

public final class VideoCameraData {

    private final int id;
    private final String sourceDataUrl;
    private final String tokenDataUrl;

    public VideoCameraData(int id, String sourceDataUrl, String tokenDataUrl) {
        this.id = id;
        this.sourceDataUrl = sourceDataUrl;
        this.tokenDataUrl = tokenDataUrl;
    }

    public int id() {
        return id;
    }

    public String sourceDataUrl() {
        return sourceDataUrl;
    }

    public String tokenDataUrl() {
        return tokenDataUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (VideoCameraData) obj;
        return this.id == that.id &&
                Objects.equals(this.sourceDataUrl, that.sourceDataUrl) &&
                Objects.equals(this.tokenDataUrl, that.tokenDataUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceDataUrl, tokenDataUrl);
    }

    @Override
    public String toString() {
        return "VideoCameraData[" +
                "id=" + id + ", " +
                "sourceDataUrl=" + sourceDataUrl + ", " +
                "tokenDataUrl=" + tokenDataUrl + ']';
    }

}
