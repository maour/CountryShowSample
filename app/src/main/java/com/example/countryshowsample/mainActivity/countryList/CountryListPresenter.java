package com.example.countryshowsample.mainActivity.countryList;


import com.example.countryshowsample.data.repository.CountryShowRepository;

import javax.inject.Inject;

public class CountryListPresenter implements CountryListContract.Presenter {

    CountryShowRepository mRepository;
    CountryListContract.View mView;

    @Inject
    public CountryListPresenter(CountryShowRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void takeView(CountryListContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
