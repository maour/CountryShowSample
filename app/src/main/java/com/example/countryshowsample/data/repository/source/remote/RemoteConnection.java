package com.example.countryshowsample.data.repository.source.remote;


import com.example.countryshowsample.data.CountryListModel;
import com.example.countryshowsample.data.repository.CountryShowSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class RemoteConnection implements CountryShowSource {

    private RemoteService mService;

    @Inject
    public RemoteConnection(RemoteService service) {
        mService = service;
    }

    @Override
    public Flowable<List<CountryListModel>> getCountryList() {
        return null;
    }

    @Override
    public void addCountry(List<CountryListModel> country) {

    }

    public Flowable<List<CountryListModel>> fetchCountryList() {
        return mService.getCountryList();
    }
}
