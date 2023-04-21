package com.example.pmd_l2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pmd_l2.entity.IDpersonIDurl;

import java.util.List;

@Dao
public interface IDpersonIDurlDao {

    @Insert
    void insert(IDpersonIDurl user);

    @Delete
    void delete(IDpersonIDurl user);

    @Query("SELECT * FROM idpersonidurl")
    List<IDpersonIDurl> getAll();

    @Query("SELECT * FROM idpersonidurl WHERE id IN (:id)")
    IDpersonIDurl getPersonID(String id);

    @Query("SELECT idurl FROM IDpersonIDurl WHERE idperson IN (:id)")
    List<String> getEpisodes(String id);
}
