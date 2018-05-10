package com.example.countryshowsample.data.repository;


import com.example.countryshowsample.data.CountryListModel;
import com.example.countryshowsample.data.repository.source.local.LocalConnection;
import com.example.countryshowsample.data.repository.source.remote.RemoteConnection;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class CountryShowRepository implements CountryShowSource {

    private static final String TAG = "Repository";

    private RemoteConnection mRemote;
    private LocalConnection mLocal;

    @Inject
    public CountryShowRepository(RemoteConnection remote, LocalConnection local) {
        mRemote = remote;
        mLocal = local;
    }

    @Override
    public Flowable<List<CountryListModel>> getCountryList() {
        reloadCountryData();

        return mLocal.getCountryList();
    }

    @Override
    public void addCountry(List<CountryListModel> countries) {
        mLocal.addCountry(countries);
    }

    private void reloadCountryData() {
        mRemote.fetchCountryList()
                .subscribeOn(Schedulers.io())
                .subscribe(countryList ->
                                storeOnDB(countryList),
                        Throwable::printStackTrace);

    }

    private void storeOnDB(List<CountryListModel> responseList) {
        mLocal.addCountry(responseList);
    }
}
