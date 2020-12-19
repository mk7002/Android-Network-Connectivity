package com.milindev.networkconnectivitylibrary;

import com.milindev.networkconnectivitylibrary.builder.NetworkSettings;
import com.milindev.networkconnectivitylibrary.connectivity.SocketConnectivity;
import com.milindev.networkconnectivitylibrary.error.ErrorHandler;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class Network {

    public static Single<Boolean> checkInternetConnectivity() {
        NetworkSettings settings = NetworkSettings.create();
        SocketConnectivity socketConnectivity = new SocketConnectivity();
        return checkInternetConnectivity(socketConnectivity,
                settings.host,
                settings.port,
                settings.timeout,
                settings.httpResponse,
                settings.errorHandler);
    }

    public static Observable<Boolean> observeInternetConnectivity() {
        NetworkSettings settings = NetworkSettings.create();
        SocketConnectivity socketConnectivity = new SocketConnectivity();
        return observeInternetConnectivity(socketConnectivity,
                settings,
                settings.host,
                settings.port,
                settings.timeout,
                settings.httpResponse,
                settings.errorHandler);
    }

    public static Observable<Boolean> observeInternetConnectivity(NetworkSettings settings) {
        SocketConnectivity socketConnectivity = new SocketConnectivity();
        return observeInternetConnectivity(socketConnectivity,
                settings,
                settings.host,
                settings.port,
                settings.timeout,
                settings.httpResponse,
                settings.errorHandler);
    }

    public static Single<Boolean> checkInternetConnectivity(NetworkSettings settings) {
        SocketConnectivity socketConnectivity = new SocketConnectivity();
        return checkInternetConnectivity(socketConnectivity,
                settings.host,
                settings.port,
                settings.timeout,
                settings.httpResponse,
                settings.errorHandler);
    }


    private static Single<Boolean> checkInternetConnectivity(
            final SocketConnectivity socketConnectivity,
            final String host,
            final int port,
            final int timeoutInMs,
            final int httpResponse,
            final ErrorHandler errorHandler) {
        return socketConnectivity.checkInternetConnectivity(host, port, timeoutInMs, httpResponse, errorHandler);
    }

    private static Observable<Boolean> observeInternetConnectivity(
            final SocketConnectivity socketConnectivity,
            final NetworkSettings settings,
            final String host,
            final int port,
            final int timeoutInMs,
            final int httpResponse,
            final ErrorHandler errorHandler) {
        return socketConnectivity.observeInternetConnectivity(settings, host, port, timeoutInMs, httpResponse, errorHandler);
    }

}
