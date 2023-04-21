package com.example.pmd_l2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "idpersonidurl", foreignKeys = {
        @ForeignKey(entity = Details.class,
                parentColumns = "idperson",
                childColumns = "idperson"),
        @ForeignKey(entity = Urlepisode.class,
                parentColumns = "idurl",
                childColumns = "idurl")}
)


public class IDpersonIDurl {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public String id;
    @ColumnInfo(name = "idperson")
    public String idPerson;
    @ColumnInfo(name = "idurl")
    public String idEpisode;

    public IDpersonIDurl(String id, String idPerson, String idEpisode) {
        this.id = id;
        this.idPerson = idPerson;
        this.idEpisode = idEpisode;
    }
}
