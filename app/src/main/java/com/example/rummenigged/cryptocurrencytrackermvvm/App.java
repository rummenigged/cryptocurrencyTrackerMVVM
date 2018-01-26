package com.example.rummenigged.cryptocurrencytrackermvvm;

import android.app.Application;

import com.example.rummenigged.cryptocurrencytrackermvvm.data.repository.ApiCacheCryptocurrencyRepository;
import com.example.rummenigged.cryptocurrencytrackermvvm.data.repository.ApiCryptocurrencyRepository;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.repository.CryptoCurrencyRepository;

/**
 * Created by rummenigged on 24/01/18.
 */

public class App extends Application{

    private static CryptoCurrencyRepository repository;

    public static CryptoCurrencyRepository getCryptoCurrencyRepository(){
        return App.repository;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CryptoCurrencyRepository apiRepository = new ApiCryptocurrencyRepository();
        CryptoCurrencyRepository apiCacheRepository = new ApiCacheCryptocurrencyRepository(apiRepository);
        App.repository = apiCacheRepository;
    }
}
