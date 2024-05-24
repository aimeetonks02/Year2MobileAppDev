package com.example.a21010398;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface bridgeDao {
    @Insert
    void insert(bridge bridge);

    @Update
    void update(bridge bridge);

    @Query("SELECT * FROM bridge_table")
    LiveData<List<bridge>> getAllBridges();
}
