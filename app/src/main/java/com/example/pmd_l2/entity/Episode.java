package com.example.pmd_l2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "episode")
public class Episode {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idepisode")
    public String id;
    @ColumnInfo(name = "description")
    public String description;

    public Episode(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
