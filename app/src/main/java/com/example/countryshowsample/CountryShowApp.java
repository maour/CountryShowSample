package com.example.countryshowsample;

import android.app.Activity;

import com.example.countryshowsample.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class CountryShowApp extends DaggerApplication {

    @Inject
    AndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


}
