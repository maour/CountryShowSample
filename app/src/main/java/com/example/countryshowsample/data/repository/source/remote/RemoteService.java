package com.example.countryshowsample.data.repository.source.remote;


import com.example.countryshowsample.data.CountryListModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RemoteService {

    @GET("countries")
    Single<List<CountryListModel>> getCountryList();
}
