package com.example.a21010398;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class bridgeRepository {
    private bridgeDao bridgeDao;
    private LiveData<List<bridge>> allBridges;

    public bridgeRepository(Application application) {
        bridgeDatabase database = bridgeDatabase.getInstance(application);
        bridgeDao = database.bridgeDao();
        allBridges = bridgeDao.getAllBridges();
    }

    public void insert(bridge bridge) {
        new InsertBridgeAsyncTask(bridgeDao).execute(bridge);
    }

    public void update(bridge bridge) {
        new UpdateBridgeAsyncTask(bridgeDao).execute(bridge);
    }

    public LiveData<List<bridge>> getAllBridges() {
        return allBridges;
    }

    private static class InsertBridgeAsyncTask extends AsyncTask<bridge, Void, Void> {
        private bridgeDao bridgeDao;

        private InsertBridgeAsyncTask(bridgeDao bridgeDao) {
            this.bridgeDao = bridgeDao;
        }

        @Override
        protected Void doInBackground(bridge... bridges) {
            bridgeDao.insert(bridges[0]);
            return null;
        }
    }

    private static class UpdateBridgeAsyncTask extends AsyncTask<bridge, Void, Void> {
        private bridgeDao bridgeDao;

        private UpdateBridgeAsyncTask(bridgeDao bridgeDao) {
            this.bridgeDao = bridgeDao;
        }

        @Override
        protected Void doInBackground(bridge... bridges) {
            bridgeDao.update(bridges[0]);
            return null;
        }
    }
}
