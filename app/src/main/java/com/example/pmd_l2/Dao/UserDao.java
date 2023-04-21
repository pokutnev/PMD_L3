package com.example.pmd_l2.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pmd_l2.entity.Details;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(Details user);

    @Delete
    void delete(Details user);

    @Query("SELECT * FROM details")
    List<Details> getAll();

    @Query("SELECT * FROM details WHERE idperson IN (:id)")
    Details getPersonID(String id);

}
