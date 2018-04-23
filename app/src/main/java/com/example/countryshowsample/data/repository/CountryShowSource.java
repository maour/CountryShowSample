package com.example.countryshowsample.data.repository;


import android.arch.lifecycle.LiveData;

import com.example.countryshowsample.data.CountryListModel;

import java.util.List;

public interface CountryShowSource {
    LiveData<List<CountryListModel>> getCountryList();

    void addCountry(List<CountryListModel> country);
}
