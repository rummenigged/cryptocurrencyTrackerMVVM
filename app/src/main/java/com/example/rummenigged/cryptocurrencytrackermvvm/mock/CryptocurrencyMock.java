package com.example.rummenigged.cryptocurrencytrackermvvm.mock;

import com.example.rummenigged.cryptocurrencytrackermvvm.data.entity.CryptocurrencyEntity;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rummenigged on 22/01/18.
 */

public class CryptocurrencyMock {

    public static List<CryptocurrencyEntity> BuildCryptocurrencyList(int qtd){
        List<CryptocurrencyEntity> mockList = new ArrayList<>();
        CryptocurrencyEntity mockCryptocurrency;
        for (int i = 0; i < qtd; i++) {
            mockCryptocurrency = new CryptocurrencyEntity("bitcoin" + i
                    , "Bitcoin" + 1
                    ,"BTC" + 1
                    ,1
                    ,10078.3
                    ,1.0
                    ,8805600000.0
                    ,169692993275.0
                    ,16837462.0
                    ,16837462.0
                    ,21000000.0
                    ,-0.35
                    ,1517424868.0
                    ,-10.65
                    ,-10.65);
            mockList.add(mockCryptocurrency);
        }
        return mockList;
    }
}
