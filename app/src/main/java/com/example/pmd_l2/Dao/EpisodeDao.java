package com.example.pmd_l2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.pmd_l2.entity.Episode;

import java.util.List;

@Dao
public interface EpisodeDao {

    @Insert
    void insert(Episode user);

    @Delete
    void delete(Episode user);

    @Query("SELECT * FROM episode")
    List<Episode> getAll();

    @Query("SELECT * FROM episode WHERE idepisode IN (:id)")
    Episode getPersonID(String id);

    @Query("SELECT description FROM episode")
    List<String> getDescriptions();

}
