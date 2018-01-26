package com.example.rummenigged.cryptocurrencytrackermvvm.data.repository;

import android.util.Log;

import com.example.rummenigged.cryptocurrencytrackermvvm.data.entity.CryptocurrencyEntity;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.repository.CryptoCurrencyRepository;

import java.util.List;

/**
 * Created by rummenigged on 24/01/18.
 */

public class ApiCacheCryptocurrencyRepository implements CryptoCurrencyRepository{

    private final String TAG = ApiCacheCryptocurrencyRepository.class.getSimpleName();
    private CryptoCurrencyRepository repository;
    private List<CryptocurrencyEntity> lastResponse;

    public ApiCacheCryptocurrencyRepository(CryptoCurrencyRepository repository){
        this.repository = repository;
    }

    @Override
    public void getCryptocurrencyList(Callback<List<CryptocurrencyEntity>> callback) {

        if (lastResponse != null){
            Log.d(TAG, "Get Data From Cache");
            callback.onResponse(lastResponse);
        }else{
            repository.getCryptocurrencyList(new Callback<List<CryptocurrencyEntity>>() {
                @Override
                public void onResponse(List<CryptocurrencyEntity> cryptocurrencyList) {
                    lastResponse = cryptocurrencyList;
                    callback.onResponse(lastResponse);
                }

                @Override
                public void onError(Throwable t) {
                    callback.onError(t);
                }
            });
        }
    }

    @Override
    public void refreshCryptocurrencyList(Callback<List<CryptocurrencyEntity>> callback) {
        repository.refreshCryptocurrencyList(new Callback<List<CryptocurrencyEntity>>() {
            @Override
            public void onResponse(List<CryptocurrencyEntity> cryptocurrencyList) {
                callback.onResponse(cryptocurrencyList);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }
}
