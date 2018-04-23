package com.example.countryshowsample.mainActivity.countryListModelView;


import android.arch.lifecycle.ViewModelProvider;

import com.example.countryshowsample.data.repository.CountryShowRepository;

import javax.inject.Inject;

public class CountryListVMFactory implements ViewModelProvider.Factory {

    private final CountryShowRepository mRepository;

    @Inject
    public CountryListVMFactory(CountryShowRepository repository) {
        mRepository = repository;
    }

    @Override
    public CountryListViewModel create(Class modelClass) {
        return new CountryListViewModel(mRepository);
    }
}
