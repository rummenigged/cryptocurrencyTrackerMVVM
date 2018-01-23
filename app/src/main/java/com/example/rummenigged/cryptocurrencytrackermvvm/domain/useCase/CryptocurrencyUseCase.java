package com.example.rummenigged.cryptocurrencytrackermvvm.domain.useCase;

import android.util.Log;

import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.repository.CryptoCurrencyRepository;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by rummenigged on 22/01/18.
 */

public class CryptocurrencyUseCase {

    public interface Callback<T>{
        void onCompleted(T t);
        void onError(Throwable t);
    }

    private CryptoCurrencyRepository repository;

    public CryptocurrencyUseCase(CryptoCurrencyRepository repository) {
        this.repository = repository;
    }

    public void getCryptocurrencies(Callback<List<Cryptocurrency>> callback){
        repository.getCryptocurrencyList(new CryptoCurrencyRepository.Callback<List<Cryptocurrency>>() {
            @Override
            public void onResponse(List<Cryptocurrency> cryptocurrencyList) {
                callback.onCompleted(cryptocurrencyList);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError: " + t.getMessage());
                callback.onError(t);
            }
        });

    }

    public void calculateAmountTotal(List<Cryptocurrency> list, Callback<Double> doubleCallback) {
        double amountTotal = 0;
        for (Cryptocurrency c: list) {
            amountTotal += c.getPrice();
        }

        doubleCallback.onCompleted(amountTotal);
    }

}
