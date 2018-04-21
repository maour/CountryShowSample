package com.example.countryshowsample.mainActivity.countryList;


import com.example.countryshowsample.BasePresenter;
import com.example.countryshowsample.BaseView;

public interface CountryListContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}