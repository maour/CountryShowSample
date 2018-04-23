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

    @Override
    public void reloadCountryList() {
        mView.showLoading(true);
        mRepository.getCountryList().observeForever(
                countryListModels -> {
                    if (countryListModels != null && countryListModels.size() > 0) {
                        mView.showCountryList(countryListModels);
                        mView.showMessage("Success");
                    } else {
                        mView.showMessage("Failed");
                    }
                    mView.showLoading(false);
                }
        );
    }
}
