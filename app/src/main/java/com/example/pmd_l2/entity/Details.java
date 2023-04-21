package com.example.pmd_l2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "details")
public class Details{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idperson")
    public String id;

    @ColumnInfo(name = "persons")
    @NonNull
    String name;
    @ColumnInfo(name = "photo")
    @NonNull
    String image;

    public Details(@NonNull String id,  @NonNull String name, @NonNull String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }


    @NonNull
    public String getName() {
        return name;
    }


    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getImage() {
        return image;
    }


    public void setImage(@NonNull String image) {
        this.image = image;
    }

}