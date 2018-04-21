package com.example.countryshowsample.di;

import android.app.Application;
import android.content.Context;

import com.example.countryshowsample.data.repository.CountryShowRepository;
import com.example.countryshowsample.data.repository.source.local.LocalConnection;
import com.example.countryshowsample.data.repository.source.remote.RemoteConnection;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Binds
    @Singleton
    abstract Context bindContext(Application application);

    @Provides
    @Singleton
    static CountryShowRepository provideCountryShowRepository(RemoteConnection remote, LocalConnection local) {
        return new CountryShowRepository(remote, local);
    }

    @Provides
    @Singleton
    static RemoteConnection provideRemoteConnection() {
        return new RemoteConnection();
    }

    @Provides
    @Singleton
    static LocalConnection provideLocalConnection(Context context) {
        return new LocalConnection(context);
    }


}