package com.example.countryshowsample.data.repository.source.local;


import android.content.Context;

import com.example.countryshowsample.data.repository.CountryShowSource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocalConnection implements CountryShowSource{

    private Context mContext;

    @Inject
    public LocalConnection(Context mContext) {
        this.mContext = mContext;
    }
}
