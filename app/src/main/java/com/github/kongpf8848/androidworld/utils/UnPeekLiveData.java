package com.github.kongpf8848.androidworld.utils;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.HashMap;
import java.util.Map;

public class UnPeekLiveData<T> extends MutableLiveData<T> {

    private final HashMap<Integer, Boolean> observers = new HashMap();

    public UnPeekLiveData() {
        super();
    }

    public UnPeekLiveData(T value) {
        super(value);
    }


    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        Integer storeId = System.identityHashCode(observer);
        observers.putIfAbsent(storeId, true);
        super.observe(owner, t -> {
            if (!observers.get(storeId)) {
                observers.put(storeId, true);
                observer.onChanged(t);
            }
        });
    }


    @Override
    public void setValue(T value) {
        for (Map.Entry<Integer, Boolean> entry : observers.entrySet()) {
            entry.setValue(false);
        }
        super.setValue(value);
    }

    @Override
    public void postValue(T value) {
        for (Map.Entry<Integer, Boolean> entry : observers.entrySet()) {
            entry.setValue(false);
        }
        super.postValue(value);
    }
}
