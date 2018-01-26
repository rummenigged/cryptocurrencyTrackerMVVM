package com.example.rummenigged.cryptocurrencytrackermvvm.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.useCase.CryptocurrencyUseCase;
import com.example.rummenigged.cryptocurrencytrackermvvm.util.SingleLiveEvent;
import com.example.rummenigged.cryptocurrencytrackermvvm.util.SortFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * Created by rummenigged on 22/01/18.
 */

public class CryptocurrencyViewModel extends ViewModel{
    private MutableLiveData<List<Cryptocurrency>> ldCryptocurrencyList;
    private MutableLiveData<String> ldError;
    private MutableLiveData<String> ldAmountTotal;
    private SingleLiveEvent<String> sleSnackBarText;

    private CryptocurrencyUseCase useCase;

    public CryptocurrencyViewModel(CryptocurrencyUseCase useCase) {
        this.useCase = useCase;
        ldCryptocurrencyList = new MutableLiveData<>();
        ldError = new MutableLiveData<>();
        ldAmountTotal = new MutableLiveData<>();
        sleSnackBarText = new SingleLiveEvent<>();
    }

    public MutableLiveData<List<Cryptocurrency>> getLdCryptocurrencyList() {
        return ldCryptocurrencyList;
    }

    public void getListCryptocurrency(){
        useCase.getCryptocurrencies(new CryptocurrencyUseCase.Callback<List<Cryptocurrency>>() {
            @Override
            public void onCompleted(List<Cryptocurrency> cryptocurrencyList) {
                ldCryptocurrencyList.setValue(cryptocurrencyList);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "onError: " + t.getMessage());
                ldError.setValue(t.getMessage());
            }
        });
    }

    public void refreshCryptocurrencyList() {
        if (ldCryptocurrencyList != null){
            ldCryptocurrencyList.getValue().clear();
        }
        useCase.refreshCryptocurrencyList(new CryptocurrencyUseCase.Callback<List<Cryptocurrency>>(){
            @Override
            public void onCompleted(List<Cryptocurrency> list) {
                ldCryptocurrencyList.setValue(list);
                sleSnackBarText.setValue("Cryptocurrecies Atualizadas Com Sucesso");
            }

            @Override
            public void onError(Throwable t) {
                ldError.setValue(t.getMessage());
            }
        });
    }

    public MutableLiveData<String> getLdError() {
        return ldError;
    }

    public MutableLiveData<String> getLdAmountTotal() {
        return ldAmountTotal;
    }

    public SingleLiveEvent<String> getSleSnackBarText() {
        return sleSnackBarText;
    }

    public void sortList() {
        List<Cryptocurrency> list = new ArrayList<>();
        list.addAll(ldCryptocurrencyList.getValue());
        Collections.sort(list, SortFactory.sortByPrice(SortFactory.DECREASING));
        ldCryptocurrencyList.setValue(list);
        sleSnackBarText.setValue("Cryptocurrecies Ordenadas Com Sucesso");
    }

    static class CryptocurrencyViewModelFactory implements ViewModelProvider.Factory{

        private CryptocurrencyUseCase useCase;

        public CryptocurrencyViewModelFactory(CryptocurrencyUseCase useCase){
            this.useCase = useCase;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if(modelClass.isAssignableFrom(CryptocurrencyViewModel.class)){
                return (T) new CryptocurrencyViewModel(this.useCase);
            }

            throw new IllegalArgumentException("Unknow View Model Class");
        }
    }
}
