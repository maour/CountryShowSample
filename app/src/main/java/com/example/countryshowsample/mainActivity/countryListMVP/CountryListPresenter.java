package com.example.countryshowsample.mainActivity.countryListMVP;


import android.arch.lifecycle.MutableLiveData;

import com.example.countryshowsample.data.CountryListModel;
import com.example.countryshowsample.data.repository.CountryShowRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CountryListPresenter implements CountryListContract.Presenter {

    CountryShowRepository mRepository;
    CountryListContract.View mView;

    MutableLiveData<List<CountryListModel>> countryListModel = new MutableLiveData<>();
    private Disposable mDisposable;

    @Inject
    public CountryListPresenter(CountryShowRepository mRepository) {
        this.mRepository = mRepository;

        countryListModel.observeForever(countryListModels -> {
            if (countryListModels != null && countryListModels.size() > 0) {
                mView.showCountryList(countryListModels);
                mView.showMessage("Success");
            } else {
                mView.showMessage("Failed");
            }
            mView.showLoading(false);
        });
    }

    @Override
    public void takeView(CountryListContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mDisposable.dispose();
        mView = null;
    }

    @Override
    public void reloadCountryList() {
        mView.showLoading(true);

        mDisposable = mRepository.getCountryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(countryListModels -> {
                            countryListModel.setValue(countryListModels);
                            mView.showMessage("Success");
                        },
                        e -> mView.showMessage("Failed")
                );


    }
}
