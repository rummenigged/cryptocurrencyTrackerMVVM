package com.example.rummenigged.cryptocurrencytrackermvvm.util;

import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;

import java.util.Comparator;
import java.util.List;

/**
 * Created by rummenigged on 25/01/18.
 */

public class SortFactory {

    public static final int GROWING = 0;
    public static final int DECREASING = 1;

    public static Comparator<Cryptocurrency> sortByPrice(int sequence){
        Comparator<Cryptocurrency> sortByPrice = null;
        switch (sequence){
            case GROWING:
                sortByPrice = (first, second) ->
                        first.getPrice().compareTo(second.getPrice());
            break;

            case DECREASING:
                sortByPrice = (first, second) ->
                        second.getPrice().compareTo(first.getPrice());
            break;
        }


        return sortByPrice;
    }
}
