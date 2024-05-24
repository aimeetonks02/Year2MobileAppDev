package com.example.a21010398;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bridge_table")
public class bridge {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private String status;

    public bridge(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
