package com.example.countryshowsample.mainActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.countryshowsample.R;
import com.example.countryshowsample.mainActivity.countryList.CountryListFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    CountryListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content);
    }

    private void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment, int frameID) {
        FragmentTransaction frg = fragmentManager.beginTransaction();
        frg.add(frameID, fragment);
        frg.commit();
    }
}
