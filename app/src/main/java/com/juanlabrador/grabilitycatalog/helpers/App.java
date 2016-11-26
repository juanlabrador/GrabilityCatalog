package com.juanlabrador.grabilitycatalog.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import com.juanlabrador.grabilitycatalog.R;

/**
 * Created by juanlabrador on 26/11/16.
 */

public class App extends MultiDexApplication {

    private InternetReceiver receiver;

    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new InternetReceiver();
        registerReceiver();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterReceiver();
    }

    public void registerReceiver() {
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void unregisterReceiver() {
        try {
            unregisterReceiver(receiver);
        } catch (IllegalArgumentException e) {}
    }

    public class InternetReceiver extends BroadcastReceiver {

        public InternetReceiver() {}

        @Override
        public void onReceive(Context context, Intent intent) {
            // If already have internet
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

            boolean isConnected = activeNetInfo != null && activeNetInfo.isConnected();

            if (isConnected) {
                // Hacer algo
            } else {
                Toast.makeText(context, R.string.message_without_internet, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
