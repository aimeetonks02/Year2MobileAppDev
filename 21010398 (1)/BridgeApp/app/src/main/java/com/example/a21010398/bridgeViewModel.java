package com.example.a21010398;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class bridgeViewModel extends AndroidViewModel {
    private bridgeRepository repository;
    private LiveData<List<bridge>> allBridges;

    public bridgeViewModel(@NonNull Application application) {
        super(application);
        repository = new bridgeRepository(application);
        allBridges = repository.getAllBridges();
    }

    public void insert(bridge bridge) {
        repository.insert(bridge);
    }

    public void update(bridge bridge) {
        repository.update(bridge);
    }

    public LiveData<List<bridge>> getAllBridges() {
        return allBridges;
    }
}
