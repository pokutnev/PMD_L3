package com.example.pmd_l2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pmd_l2.Dao.EpisodeDao;
import com.example.pmd_l2.Dao.IDpersonIDepisodeDao;
import com.example.pmd_l2.Dao.IDpersonIDurlDao;
import com.example.pmd_l2.Dao.UrlDao;
import com.example.pmd_l2.Dao.UserDao;
import com.example.pmd_l2.entity.Details;
import com.example.pmd_l2.entity.Episode;
import com.example.pmd_l2.entity.IDpersonIDepisode;
import com.example.pmd_l2.entity.IDpersonIDurl;
import com.example.pmd_l2.entity.Urlepisode;

@Database(entities = {Details.class, Episode.class, Urlepisode.class, IDpersonIDepisode.class, IDpersonIDurl.class}, version = 2)
public abstract class AppDB extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract EpisodeDao episodeDao();
    public abstract UrlDao urlDao();
    public abstract IDpersonIDepisodeDao iDpersonIDepisodeDao();
    public abstract IDpersonIDurlDao iDpersonIDurl();

}
