package com.prototype.aggregationservice.helper;

import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;

public final class WebHelper {

    private WebHelper() {
    }

    public static SimpleHttpRequest createRequest(String url) {
        return SimpleRequestBuilder.get(url).build();
    }

}
