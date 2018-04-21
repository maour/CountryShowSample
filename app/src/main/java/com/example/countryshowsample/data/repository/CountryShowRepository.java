package com.example.countryshowsample.data.repository;


import com.example.countryshowsample.data.repository.source.local.LocalConnection;
import com.example.countryshowsample.data.repository.source.remote.RemoteConnection;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CountryShowRepository implements CountryShowSource {

    RemoteConnection mRemote;
    LocalConnection mLocal;

    @Inject
    public CountryShowRepository(RemoteConnection mRemote, LocalConnection mLocal) {
        this.mRemote = mRemote;
        this.mLocal = mLocal;
    }
}
