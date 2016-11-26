package com.juanlabrador.grabilitycatalog.taks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.juanlabrador.grabilitycatalog.interfaces.AsyncFeedResponse;
import com.juanlabrador.grabilitycatalog.models.Data;
import com.juanlabrador.grabilitycatalog.models.Feed;
import com.juanlabrador.grabilitycatalog.services.CatalogApi;
import com.juanlabrador.grabilitycatalog.services.RestCatalogAdapter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by conduct19 on 26/10/2016.
 */

public class FeedContentTask extends AsyncTask<Void, Void, Data> {

    private static final String TAG = "FeedContentTask";
    private Context context;
    private CatalogApi catalogApi;
    public AsyncFeedResponse delegate = null;

    public FeedContentTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Data doInBackground(Void... params) {

        catalogApi = RestCatalogAdapter.retrofit.create(CatalogApi.class);

        Call<Data> call = catalogApi.getApplications(200);

        try {
            Response<Data> response =  call.execute();
            return response.body();
        } catch (NullPointerException | IOException e) {
            Log.e(TAG, e.getMessage());
        }

        return new Data();

    }

    @Override
    protected void onPostExecute(Data contentResponse) {
        delegate.processFinish(contentResponse);
    }

}
