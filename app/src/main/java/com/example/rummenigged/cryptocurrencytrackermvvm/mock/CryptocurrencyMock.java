package com.example.rummenigged.cryptocurrencytrackermvvm.mock;

import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rummenigged on 22/01/18.
 */

public class CryptocurrencyMock {

    public static List<Cryptocurrency> BuildCryptocurrencyList(int qtd){
        List<Cryptocurrency> mockList = new ArrayList<>();
        Cryptocurrency mockCryptocurrency;
        for (int i = 0; i < qtd; i++) {
            mockCryptocurrency = new Cryptocurrency("bitcoin" + i
                                                    ,"Bitcoin" + i
                                                    ,"BTC" + i
                                                    ,10827.1
                                                    ,-5.85
                                                    ,23.64);
            mockList.add(mockCryptocurrency);
        }
        return mockList;
    }
}
