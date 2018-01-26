package com.example.rummenigged.cryptocurrencytrackermvvm.domain.repository;

import com.example.rummenigged.cryptocurrencytrackermvvm.data.entity.CryptocurrencyEntity;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.useCase.CryptocurrencyUseCase;

import java.util.List;

/**
 * Created by rummenigged on 22/01/18.
 */

public interface CryptoCurrencyRepository {
    interface Callback<T>{
        void onResponse(T t);
        void onError(Throwable t);
    }
    void getCryptocurrencyList(Callback<List<CryptocurrencyEntity>> callback);

    void refreshCryptocurrencyList(Callback<List<CryptocurrencyEntity>> callback);
}
