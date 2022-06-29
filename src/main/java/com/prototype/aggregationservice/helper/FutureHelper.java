package com.prototype.aggregationservice.helper;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class FutureHelper {

    private FutureHelper() {

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
