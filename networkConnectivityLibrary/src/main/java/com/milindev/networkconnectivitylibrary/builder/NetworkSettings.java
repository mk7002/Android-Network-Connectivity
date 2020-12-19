package com.milindev.networkconnectivitylibrary.builder;

import com.milindev.networkconnectivitylibrary.error.ErrorHandler;

import java.net.HttpURLConnection;

public final class NetworkSettings {

    public int interval;
    public String host;
    public int port;
    public int timeout;
    public int httpResponse;
    public int initialIntervalInMs;
    public ErrorHandler errorHandler;

    private NetworkSettings(Builder builder) {
        this(builder.DEFAULT_HOST,
                builder.DEFAULT_PORT,
                builder.DEFAULT_TIMEOUT_IN_MILLIS,
                builder.httpResponse,
                builder.interval,
                builder.initialIntervalInMs,
                builder.errorHandler);
    }

    private NetworkSettings(String host,
                            int port,
                            int timeout,
                            int httpResponse,
                            int interval,
                            int initialIntervalInMs,
                            ErrorHandler errorHandler) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.httpResponse = httpResponse;
        this.errorHandler = errorHandler;
        this.interval = interval;
        this.initialIntervalInMs = initialIntervalInMs;
    }

    public static NetworkSettings create() {
        return new Builder().build();
    }

    public static class Builder {
        private String DEFAULT_HOST = "www.google.com";
        private String HTTP_PROTOCOL = "http://";
        private String HTTPS_PROTOCOL = "https://";
        private int DEFAULT_TIMEOUT_IN_MILLIS = 2500;
        private int DEFAULT_PORT = 80;
        private int interval = 1000;
        private int initialIntervalInMs = 1000;
        private int httpResponse = HttpURLConnection.HTTP_NO_CONTENT;
        private ErrorHandler errorHandler = (exception, message) -> {

        };

        public Builder() {

        }

        public Builder setInitialIntervalInMs(int initialIntervalInMs) {
            this.initialIntervalInMs = initialIntervalInMs;
            return this;
        }

        public Builder setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public Builder setDEFAULT_HOST(String DEFAULT_HOST) {
            this.DEFAULT_HOST = DEFAULT_HOST;
            return this;
        }

        public Builder setHTTP_PROTOCOL(String HTTP_PROTOCOL) {
            this.HTTP_PROTOCOL = HTTP_PROTOCOL;
            return this;
        }


        public Builder setHTTPS_PROTOCOL(String HTTPS_PROTOCOL) {
            this.HTTPS_PROTOCOL = HTTPS_PROTOCOL;
            return this;
        }


        public Builder setDEFAULT_TIMEOUT_IN_MILLIS(int DEFAULT_TIMEOUT_IN_MILLIS) {
            this.DEFAULT_TIMEOUT_IN_MILLIS = DEFAULT_TIMEOUT_IN_MILLIS;
            return this;
        }


        public Builder setErrorHandler(ErrorHandler errorHandler) {
            this.errorHandler = errorHandler;
            return this;
        }

        public Builder setDEFAULT_PORT(int DEFAULT_PORT) {
            this.DEFAULT_PORT = DEFAULT_PORT;
            return this;
        }

        public Builder setHttpResponse(int httpResponse) {
            this.httpResponse = httpResponse;
            return this;
        }

        public NetworkSettings build() {
            return new NetworkSettings(this);
        }


    }

}
