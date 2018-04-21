package com.example.countryshowsample.data.repository.source.remote;


import com.example.countryshowsample.data.repository.CountryShowSource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RemoteConnection implements CountryShowSource {


    @Inject
    public RemoteConnection() {
    }
}
