package com.apps.barclaysitunes.service;


import com.apps.barclaysitunes.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ItunesAPI {

    @GET("/search?")
    Observable<Response> getAllCountriesGeoNames(@Query("term") String enteredString, @Query("entity") String entityName);

}
