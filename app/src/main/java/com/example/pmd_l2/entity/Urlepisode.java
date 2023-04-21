package com.example.pmd_l2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "urlepisode")
public class Urlepisode {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idurl")
    public String id;
    @ColumnInfo(name = "urls")
    public String url;

    public Urlepisode(String id, String url) {
        this.id = id;
        this.url = url;
    }
}
