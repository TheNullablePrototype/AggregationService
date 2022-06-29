package com.prototype.aggregationservice.data;

public final class TokenData {

    private final String value;
    private final int ttl;

    public TokenData(String value, int ttl) {
        this.value = value;
        this.ttl = ttl;
    }

    public String getValue() {
        return value;
    }

    public int getTtl() {
        return ttl;
    }

}
