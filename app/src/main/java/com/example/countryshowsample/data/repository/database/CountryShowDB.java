package com.example.countryshowsample.data.repository.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.countryshowsample.data.CountryListModel;

@Database(entities = CountryListModel.class, version = 1, exportSchema = false)
public abstract class CountryShowDB extends RoomDatabase {

    public abstract CountryDao countryDao();

}
