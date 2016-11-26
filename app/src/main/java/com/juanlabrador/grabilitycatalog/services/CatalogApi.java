package com.juanlabrador.grabilitycatalog.services;

import com.juanlabrador.grabilitycatalog.models.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by conduct19 on 21/10/2016.
 */

public interface CatalogApi {

    @GET("us/rss/topfreeapplications/limit={limit}/json")
    Call<Data> getApplications(@Path("limit") int limit);

}
