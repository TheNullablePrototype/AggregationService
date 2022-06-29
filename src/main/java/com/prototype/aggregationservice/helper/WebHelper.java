package com.prototype.aggregationservice.utils.web;

import org.apache.hc.client5.http.async.methods.*;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class WebUtils {

    public static SimpleHttpRequest createRequest(String url) {
        return SimpleRequestBuilder.get(url).build();
    }

    public static Future<SimpleHttpResponse> createFutureResponse(Logger logger, CloseableHttpAsyncClient client, SimpleHttpRequest request) {
        return client.execute(
                SimpleRequestProducer.create(request),
                SimpleResponseConsumer.create(),
                new SimpleFutureCallback(logger, request)
        );
    }

    public static <T> CompletableFuture<T> fromFuture(Future<T> f) {
        return CompletableFuture.completedFuture(null).thenCompose(v -> {
            try {
                return CompletableFuture.completedFuture(f.get());
            } catch (InterruptedException e) {
                return CompletableFuture.failedFuture(e);
            } catch (ExecutionException e) {
                return CompletableFuture.failedFuture(e.getCause());
            }
        });
    }

}
