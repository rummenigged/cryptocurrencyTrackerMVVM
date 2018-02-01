package com.example.rummenigged.cryptocurrencytrackermvvm.data.repository;

import com.example.rummenigged.cryptocurrencytrackermvvm.data.entity.CryptocurrencyEntity;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.repository.CryptoCurrencyRepository;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.useCase.CryptocurrencyUseCase;
import com.example.rummenigged.cryptocurrencytrackermvvm.mock.CryptocurrencyMock;

import java.util.List;

/**
 * Created by rummenigged on 22/01/18.
 */

public class MockCryptocurrencyRepository implements CryptoCurrencyRepository {

    @Override
    public void getCryptocurrencyList(Callback<List<CryptocurrencyEntity>> callback) {
        callback.onResponse(CryptocurrencyMock.BuildCryptocurrencyList(10));
    }

    @Override
    public void refreshCryptocurrencyList(Callback<List<CryptocurrencyEntity>> callback) {
        callback.onResponse(CryptocurrencyMock.BuildCryptocurrencyList(10));
    }
}
