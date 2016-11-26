package com.juanlabrador.grabilitycatalog.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.juanlabrador.grabilitycatalog.R;
import com.juanlabrador.grabilitycatalog.interfaces.AsyncFeedResponse;
import com.juanlabrador.grabilitycatalog.models.Data;
import com.juanlabrador.grabilitycatalog.taks.FeedContentTask;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by conduct19 on 20/10/2016.
 */

public class SplashActivity extends BaseActivity implements AsyncFeedResponse {

    private static final String TAG = "SplashActivity";
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        data = EventBus.getDefault().getStickyEvent(Data.class);

        if(data != null) {
            startActivity();
        } else {
            final FeedContentTask asyncTask = new FeedContentTask(getApplicationContext());
            asyncTask.delegate = this;
            asyncTask.execute();
        }

    }

    @Override
    public void processFinish(Data data) {
        this.data = data;
        EventBus.getDefault().postSticky(data);
        startActivity();
    }

    public void startActivity() {
        startActivity(new Intent(this, CategoryActivity.class));
        overridePendingTransition(0, 0);
    }
}