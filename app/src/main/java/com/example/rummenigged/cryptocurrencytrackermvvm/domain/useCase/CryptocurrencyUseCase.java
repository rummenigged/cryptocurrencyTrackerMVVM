package com.example.rummenigged.cryptocurrencytrackermvvm.domain.useCase;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.rummenigged.cryptocurrencytrackermvvm.data.entity.CryptocurrencyEntity;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.repository.CryptoCurrencyRepository;
import com.example.rummenigged.cryptocurrencytrackermvvm.util.SortFactory;

import java.util.ArrayList;
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
        repository.getCryptocurrencyList(new CryptoCurrencyRepository.Callback<List<CryptocurrencyEntity>>() {
            @Override
            public void onResponse(List<CryptocurrencyEntity> cryptocurrencyList) {
                List<Cryptocurrency> convertedList = new ArrayList<>();
                for (CryptocurrencyEntity ce: cryptocurrencyList) {
                    convertedList.add(convertCryptocurrencyViewModel(ce));
                }

                callback.onCompleted(convertedList);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError: " + t.getMessage());
                callback.onError(t);
            }
        });

    }

    public void refreshCryptocurrencyList(Callback<List<Cryptocurrency>> callback){
        repository.refreshCryptocurrencyList(new CryptoCurrencyRepository.Callback<List<CryptocurrencyEntity>>() {
            @Override
            public void onResponse(List<CryptocurrencyEntity> cryptocurrencyList) {
                List<Cryptocurrency> convertedList = new ArrayList<>();
                for (CryptocurrencyEntity ce: cryptocurrencyList) {
                    convertedList.add(convertCryptocurrencyViewModel(ce));
                }

                callback.onCompleted(convertedList);
            }

            @Override
            public void onError(Throwable t) {
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

    private Cryptocurrency convertCryptocurrencyViewModel(CryptocurrencyEntity entity){
        Cryptocurrency cryptocurrency = new Cryptocurrency();
        cryptocurrency.setIdCurrency(entity.getIdCurrency());
        cryptocurrency.setName(entity.getName());
        cryptocurrency.setSymbol(entity.getSymbol());
        cryptocurrency.setRank(entity.getRank());
        cryptocurrency.setPrice(entity.getPriceUsd());
        cryptocurrency.setPercentChance24h(entity.getPercentChance24h());
        cryptocurrency.setPercentChance7d(entity.getPercentChance7d());

        return cryptocurrency;
    }
}
