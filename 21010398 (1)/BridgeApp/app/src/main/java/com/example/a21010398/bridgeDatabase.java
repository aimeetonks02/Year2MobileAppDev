package com.example.a21010398;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;

@Database(entities = bridge.class, version = 1)
public abstract class bridgeDatabase extends RoomDatabase {
    private static bridgeDatabase instance;

    public abstract bridgeDao bridgeDao();

    public static synchronized bridgeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    bridgeDatabase.class, "bridge_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}