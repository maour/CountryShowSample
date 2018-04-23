package com.example.countryshowsample.mainActivity.countryListMVP;


import com.example.countryshowsample.BasePresenter;
import com.example.countryshowsample.BaseView;
import com.example.countryshowsample.data.CountryListModel;

import java.util.List;

public interface CountryListContract {
    interface View extends BaseView {

        void showCountryList(List<CountryListModel> countryListResponse);
    }

    interface Presenter extends BasePresenter<View> {

        void reloadCountryList();
    }
}
