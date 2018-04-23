package com.example.countryshowsample.data.repository.source.local;


import android.arch.lifecycle.LiveData;

import com.example.countryshowsample.data.CountryListModel;
import com.example.countryshowsample.data.repository.CountryShowSource;
import com.example.countryshowsample.data.repository.database.CountryDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocalConnection implements CountryShowSource {

    private CountryDao mCountryDao;

    @Inject
    public LocalConnection(CountryDao countryDao) {
        mCountryDao = countryDao;
    }

    @Override
    public LiveData<List<CountryListModel>> getCountryList() {
        return mCountryDao.getAllCountryList();
    }

    @Override
    public void addCountry(List<CountryListModel> countries) {
        mCountryDao.insert(countries);
    }

}
