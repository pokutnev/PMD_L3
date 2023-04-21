package com.example.pmd_l2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "idpersonidepisode", foreignKeys = {
        @ForeignKey(entity = Details.class,
                parentColumns = "idperson",
                childColumns = "idperson"),
        @ForeignKey(entity = Episode.class,
                parentColumns = "idepisode",
                childColumns = "idepisode")})


public class IDpersonIDepisode {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public String id;
    @ColumnInfo(name = "idperson")
    public String idPerson;
    @ColumnInfo(name = "idepisode")
    public String idEpisode;

    public IDpersonIDepisode(String id, String idPerson, String idEpisode) {
        this.id = id;
        this.idPerson = idPerson;
        this.idEpisode = idEpisode;
    }
}
