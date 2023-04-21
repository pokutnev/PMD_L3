package com.example.pmd_l2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pmd_l2.entity.IDpersonIDepisode;

import java.util.List;

@Dao
public interface IDpersonIDepisodeDao {

    @Insert
    void insert(IDpersonIDepisode user);

    @Delete
    void delete(IDpersonIDepisode user);

    @Query("SELECT * FROM idpersonidepisode")
    List<IDpersonIDepisode> getAll();

    @Query("SELECT * FROM idpersonidepisode WHERE id IN (:id)")
    IDpersonIDepisode getPersonID(String id);

    @Query("SELECT idepisode FROM idpersonidepisode WHERE idperson IN (:id)")
    List<String> getEpisodeID(String id);
}
