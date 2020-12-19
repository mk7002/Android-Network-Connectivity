package com.milindev.networkconnectivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.milindev.networkconnectivitylibrary.Network;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disposable = Network
                .observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connected -> {
                    ((TextView) findViewById(R.id.tv_internet)).setText(connected ? "Connected" : "Disconnected");
                    ((TextView) findViewById(R.id.tv_internet))
                            .setTextColor(connected ?
                                    getResources().getColor(R.color.green) :
                                    getResources().getColor(R.color.red));
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null)
            disposable.dispose();
    }
}