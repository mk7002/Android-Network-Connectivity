package com.milindev.networkconnectivitylibrary.connectivity;

import android.util.Log;

import com.milindev.networkconnectivitylibrary.builder.NetworkSettings;
import com.milindev.networkconnectivitylibrary.error.ErrorHandler;
import com.milindev.networkconnectivitylibrary.observer.InternetObserving;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SocketConnectivity implements InternetObserving {


    @Override
    public Observable<Boolean> observeInternetConnectivity(NetworkSettings settings,
                                                           String host,
                                                           int port,
                                                           int timeoutInMs,
                                                           int httpResponse,
                                                           ErrorHandler errorHandler) {
        return Observable.interval(settings.initialIntervalInMs, settings.interval, TimeUnit.MILLISECONDS,
                Schedulers.io()).map(new Function<Long, Boolean>() {
            @Override public Boolean apply(@NonNull Long tick) throws Exception {
                return isConnected(host, port, timeoutInMs, errorHandler);
            }
        }).distinctUntilChanged();
    }

    @Override
    public Single<Boolean> checkInternetConnectivity(String host, int port, int timeoutInMs, int httpResponse, ErrorHandler errorHandler) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Boolean> emitter) throws Throwable {
                emitter.onSuccess(SocketConnectivity.this.isConnected(host, port, timeoutInMs, errorHandler));
            }
        });
    }

    private boolean isConnected(final String host, final int port, final int timeoutInMs,
                                  final ErrorHandler errorHandler) {
        final Socket socket = new Socket();
        return isConnected(socket, host, port, timeoutInMs, errorHandler);
    }

    private boolean isConnected(final Socket socket, final String host, final int port,
                                  final int timeoutInMs, final ErrorHandler errorHandler) {
        boolean isConnected;
        try {
            socket.connect(new InetSocketAddress(host, port), timeoutInMs);
            isConnected = socket.isConnected();
        } catch (IOException e) {
            isConnected = Boolean.FALSE;
            Log.e("TAG", "isConnected: "+e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException exception) {
                errorHandler.onnError(exception, "Could not close the socket");
            }
        }
        return isConnected;
    }
}
