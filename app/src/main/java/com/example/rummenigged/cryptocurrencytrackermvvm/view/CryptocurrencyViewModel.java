package com.example.rummenigged.cryptocurrencytrackermvvm.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.rummenigged.cryptocurrencytrackermvvm.domain.model.Cryptocurrency;
import com.example.rummenigged.cryptocurrencytrackermvvm.domain.useCase.CryptocurrencyUseCase;

import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * Created by rummenigged on 22/01/18.
 */

public class CryptocurrencyViewModel extends ViewModel{
    private MutableLiveData<List<Cryptocurrency>> ldCryptocurrencyList = new MutableLiveData<>();
    private MutableLiveData<String> ldError = new MutableLiveData<>();
    private MutableLiveData<String> ldAmountTotal = new MutableLiveData<>();

    private CryptocurrencyUseCase useCase;

    public CryptocurrencyViewModel(CryptocurrencyUseCase useCase) {
        this.useCase = useCase;
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

    public MutableLiveData<String> getLdError() {
        return ldError;
    }

    public MutableLiveData<String> getLdAmountTotal() {
        return ldAmountTotal;
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
