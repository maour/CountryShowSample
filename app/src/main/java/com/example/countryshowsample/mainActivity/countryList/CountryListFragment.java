package com.example.countryshowsample.mainActivity.countryList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.countryshowsample.R;
import com.example.countryshowsample.di.PerActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@PerActivity
public class CountryListFragment extends DaggerFragment implements CountryListContract.View {

    @Inject
    CountryListContract.Presenter presenter;

    @Inject
    public CountryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void onPause() {
        presenter.dropView();
        super.onPause();
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void showMessage(String message) {

    }
}
