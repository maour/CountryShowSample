package com.example.countryshowsample.data.repository.source.remote;


import android.arch.lifecycle.LiveData;

import com.example.countryshowsample.data.CountryListModel;
import com.example.countryshowsample.data.repository.CountryShowSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class RemoteConnection implements CountryShowSource {

    private RemoteService mService;

    @Inject
    public RemoteConnection(RemoteService service) {
        mService = service;
    }

    @Override
    public LiveData<List<CountryListModel>> getCountryList() {
        return null;
    }

    @Override
    public void addCountry(List<CountryListModel> country) {

    }

    public Single<List<CountryListModel>> fetchCountryList() {
        return mService.getCountryList();
    }
}
