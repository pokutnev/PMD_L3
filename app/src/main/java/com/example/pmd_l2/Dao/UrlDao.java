package com.example.pmd_l2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pmd_l2.entity.Urlepisode;

import java.util.List;

@Dao
public interface UrlDao {

    @Insert
    void insert(Urlepisode user);

    @Delete
    void delete(Urlepisode user);

    @Query("SELECT * FROM urlepisode")
    List<Urlepisode> getAll();

    @Query("SELECT * FROM urlepisode WHERE idurl IN (:id)")
    Urlepisode getPersonID(String id);
}
