package com.apps.barclaysitunes.rest;

import com.apps.barclaysitunes.data.TestData;
import com.apps.barclaysitunes.model.Response;
import com.apps.barclaysitunes.service.ItunesAPI;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tharakaramu on 11/15/2016.
 */

public class MockItunesApi implements ItunesAPI {

    @Override
    public Observable<Response> getAllCountriesGeoNames(String enteredString, String entityName) {
        Response response = new Gson().fromJson(TestData.ITUNES_DATA_JSON, Response.class);
        return Observable.just(response).delay(1, TimeUnit.SECONDS);

    }
}
