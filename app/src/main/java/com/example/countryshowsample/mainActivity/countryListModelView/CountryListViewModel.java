package com.example.countryshowsample.mainActivity.countryListModelView;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.countryshowsample.data.CountryListModel;
import com.example.countryshowsample.data.repository.CountryShowRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CountryListViewModel extends ViewModel {

    CountryShowRepository mRepository;

    private final MutableLiveData<List<CountryListModel>> countryListResponse = new MutableLiveData<>();

    @Inject
    CountryListViewModel(CountryShowRepository mRepository) {
        this.mRepository = mRepository;
    }

    MutableLiveData<List<CountryListModel>> countryListResponse() {
        return countryListResponse;
    }

    void reloadCountryList() {

        mRepository.getCountryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(countryListModels -> countryListResponse.setValue(countryListModels));

    }
}
