package com.example.rummenigged.cryptocurrencytrackermvvm.data.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rummenigged.cryptocurrencytrackermvvm.data.entity.CryptocurrencyEntity;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.repository.CryptoCurrencyRepository;
import com.example.rummenigged.cryptocurrencytrackermvvm.network.Webservice;
import com.example.rummenigged.cryptocurrencytrackermvvm.util.Paths;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.CacheControl;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by rummenigged on 22/01/18.
 */

public class ApiCryptocurrencyRepository implements CryptoCurrencyRepository {
    private Webservice webservice = new Retrofit.Builder()
            .baseUrl(Paths.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Webservice.class);

    @Override
    public void getCryptocurrencyList(Callback<List<CryptocurrencyEntity>> callback) {
        webservice.getCryptocurrency().enqueue(new retrofit2.Callback<JsonArray>() {
            @Override
            public void onResponse(@NonNull Call<JsonArray> call, @NonNull Response<JsonArray> response) {
                try {
                    Log.d(TAG, "Get Data From API");
                    callback.onResponse(convertJsonArray(response.body()));
                } catch (JSONException e) {
                    Log.d(TAG, "onResponse: " + e.getMessage());
                    callback.onError(e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonArray> call, @NonNull Throwable t) {
                    Log.d(TAG, "onResponse: " + t.getMessage());
                    callback.onError(t);
            }
        });
    }

    @Override
    public void refreshCryptocurrencyList(Callback<List<CryptocurrencyEntity>> callback) {
        getCryptocurrencyList(new Callback<List<CryptocurrencyEntity>>() {
            @Override
            public void onResponse(List<CryptocurrencyEntity> cryptocurrencyList) {
                Log.d(TAG, "Get Data From API By Refresh");
                callback.onResponse(cryptocurrencyList);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }

//    private static List<Cryptocurrency> convertJsonArray(JsonArray jsonArray) throws JSONException {
//        List<Cryptocurrency> cryptocurrencyList = new ArrayList<>();
//        Cryptocurrency cryptocurrency;
//
//        for(int i = 0 ; i < jsonArray.size() ; i++){
//            cryptocurrency = convertObject(jsonArray.get(i));
//            cryptocurrencyList.add(cryptocurrency);
//        }
//
//        return cryptocurrencyList;
//    }
//
//    private static Cryptocurrency convertObject(JsonElement object) throws JSONException {
//        Cryptocurrency cryptocurrency = new Cryptocurrency();
//
//        cryptocurrency.setIdCurrency(object.getAsJsonObject().get("id").getAsString());
//        cryptocurrency.setName(object.getAsJsonObject().get("name").getAsString());
//        cryptocurrency.setSymbol(object.getAsJsonObject().get("symbol").getAsString());
//        cryptocurrency.setRank(object.getAsJsonObject().get("rank").getAsInt());
//        cryptocurrency.setPrice(Double
//                                    .parseDouble(
//                                            String.format(
//                                                    Locale.US
//                                                    ,"%.4f"
//                                                    , object
//                                                        .getAsJsonObject()
//                                                        .get("price_usd")
//                                                        .getAsDouble())));
//        cryptocurrency.setPercentChance24h(object.getAsJsonObject().get("percent_change_24h").getAsDouble());
//        cryptocurrency.setPercentChance7d(object.getAsJsonObject().get("percent_change_7d").getAsDouble());
//
//        return cryptocurrency;
//    }

    private static List<CryptocurrencyEntity> convertJsonArray(JsonArray jsonArray) throws JSONException {
        List<CryptocurrencyEntity> cryptocurrencyList = new ArrayList<>();
        CryptocurrencyEntity entity;

        for(int i = 0 ; i < jsonArray.size() ; i++){
            entity = convertObject(jsonArray.get(i));
            cryptocurrencyList.add(entity);
        }

        return cryptocurrencyList;
    }

    private static CryptocurrencyEntity convertObject(JsonElement object) throws JSONException {
        CryptocurrencyEntity entity = new CryptocurrencyEntity();

        entity.setIdCurrency(object.getAsJsonObject().get("id").getAsString());
        entity.setName(object.getAsJsonObject().get("name").getAsString());
        entity.setSymbol(object.getAsJsonObject().get("symbol").getAsString());
        entity.setRank(object.getAsJsonObject().get("rank").getAsInt());
        entity.setPriceUsd(object.getAsJsonObject().get("price_usd").getAsDouble());
        entity.setPriceBtc(object.getAsJsonObject().get("price_btc").getAsDouble());
        entity.setVolumeUsd24h(object.getAsJsonObject().get("24h_volume_usd").getAsDouble());
        entity.setMarketCapUsd(object.getAsJsonObject().get("market_cap_usd").getAsDouble());
        entity.setAvailableSupply(object.getAsJsonObject().get("available_supply").getAsDouble());
        entity.setTotalSupply(object.getAsJsonObject().get("total_supply").getAsDouble());
        entity.setMaxSupply(0.0);
        entity.setPercentChange1h(object.getAsJsonObject().get("percent_change_1h").getAsDouble());
        entity.setLastUpdated(object.getAsJsonObject().get("last_updated").getAsDouble());
        entity.setPercentChance24h(object.getAsJsonObject().get("percent_change_24h").getAsDouble());
        entity.setPercentChance7d(object.getAsJsonObject().get("percent_change_7d").getAsDouble());

        return entity;
    }


}
