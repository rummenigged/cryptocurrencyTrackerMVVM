package com.example.rummenigged.cryptocurrencytrackermvvm.util;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by rummenigged on 25/01/18.
 */

public class SingleLiveEvent<T> extends MutableLiveData<T>{
    private static final String TAG = "SingleLiveEvent";

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        super.observe(owner, t -> {
            mPending.set(true);
            observer.onChanged(t);
        });
    }

    @MainThread
    public void setValue(T value) {
        mPending.set(true);
        super.setValue(value);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
