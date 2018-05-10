package com.example.countryshowsample.data.repository;


import com.example.countryshowsample.data.CountryListModel;

import java.util.List;

import io.reactivex.Flowable;

public interface CountryShowSource {
    Flowable<List<CountryListModel>> getCountryList();

    void addCountry(List<CountryListModel> country);
}
