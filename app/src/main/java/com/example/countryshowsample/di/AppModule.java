package com.example.countryshowsample.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.countryshowsample.BuildConfig;
import com.example.countryshowsample.data.repository.CountryShowRepository;
import com.example.countryshowsample.data.repository.database.CountryDao;
import com.example.countryshowsample.data.repository.database.CountryShowDB;
import com.example.countryshowsample.data.repository.source.local.LocalConnection;
import com.example.countryshowsample.data.repository.source.remote.RemoteConnection;
import com.example.countryshowsample.data.repository.source.remote.RemoteService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {

    private static final String BASE_URL = "baseURL";

    @Provides
    @Named(BASE_URL)
    static String provideBaseUrl() {
        return BuildConfig.SERVER_URL;
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(@Named(BASE_URL) String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static RemoteService provideService(Retrofit retrofit) {
        return retrofit.create(RemoteService.class);
    }

    @Provides
    @Singleton
    static RemoteConnection provideRemoteConnection(RemoteService service) {
        return new RemoteConnection(service);
    }

    @Provides
    @Singleton
    static CountryShowDB provideDatabase(Context context) {
        return Room.databaseBuilder(context, CountryShowDB.class, "CountryShow.db").build();
    }

    @Provides
    @Singleton
    static CountryDao provideCountryDao(CountryShowDB roomConnection) {
        return roomConnection.countryDao();
    }

    @Provides
    @Singleton
    static LocalConnection provideLocalConnection(CountryDao countryDao) {
        return new LocalConnection(countryDao);
    }

    @Provides
    @Singleton
    static CountryShowRepository provideCountryShowRepository(RemoteConnection remote, LocalConnection local) {
        return new CountryShowRepository(remote, local);
    }

    @Binds
    @Singleton
    abstract Context bindContext(Application application);

}