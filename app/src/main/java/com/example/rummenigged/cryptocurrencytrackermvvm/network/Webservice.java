package com.example.rummenigged.cryptocurrencytrackermvvm.network;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by rummenigged on 23/01/18.
 */

public interface Webservice {

    @GET("ticker/")
    Call<JsonArray> getCryptocurrency();
}
