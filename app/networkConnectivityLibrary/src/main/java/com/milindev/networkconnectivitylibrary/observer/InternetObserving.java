package com.milindev.networkconnectivitylibrary.observer;


import com.milindev.networkconnectivitylibrary.builder.NetworkSettings;
import com.milindev.networkconnectivitylibrary.error.ErrorHandler;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface InternetObserving {

    Observable<Boolean> observeInternetConnectivity(NetworkSettings settings, final String host, final int port,
                                                    final int timeoutInMs, final int httpResponse, final ErrorHandler errorHandler);

    Single<Boolean> checkInternetConnectivity(final String host, final int port,
                                              final int timeoutInMs, final int httpResponse, final ErrorHandler errorHandler);


}
