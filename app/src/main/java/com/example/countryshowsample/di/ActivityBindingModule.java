package com.example.countryshowsample.di;


import com.example.countryshowsample.mainActivity.MainActivity;
import com.example.countryshowsample.mainActivity.MainActivityModule;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity mainActivity();

}