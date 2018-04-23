package com.example.countryshowsample.mainActivity;


import com.example.countryshowsample.di.PerActivity;
import com.example.countryshowsample.di.PerFragment;
import com.example.countryshowsample.mainActivity.countryListMVP.CountryListContract;
import com.example.countryshowsample.mainActivity.countryListMVP.CountryListFragment;
import com.example.countryshowsample.mainActivity.countryListMVP.CountryListPresenter;
import com.example.countryshowsample.mainActivity.countryListModelView.CountryListViewModelFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract CountryListFragment countryListFragment();

    @PerActivity
    @Binds
    abstract CountryListContract.Presenter countryListPresenter(CountryListPresenter presenter);


    @PerFragment
    @ContributesAndroidInjector
    abstract CountryListViewModelFragment countryListViewModelFragment();

}
