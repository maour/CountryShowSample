package com.example.countryshowsample.data.repository.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.countryshowsample.data.Config;
import com.example.countryshowsample.data.CountryListModel;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM " + Config.Country_Table_Name)
    Flowable<List<CountryListModel>> getAllCountryList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CountryListModel> countries);

}
