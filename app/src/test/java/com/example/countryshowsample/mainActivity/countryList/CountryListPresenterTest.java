package com.example.countryshowsample.mainActivity.countryList;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.countryshowsample.data.CountryListModel;
import com.example.countryshowsample.data.repository.CountryShowRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CountryListPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public TestRule ruleT = new InstantTaskExecutorRule();

    @Mock
    CountryShowRepository repository;

    @Mock
    CountryListContract.View view;

    List<CountryListModel> countryListResponse;

    CountryListPresenter presenter;

    LiveData<List<CountryListModel>> updatedDbData;

    Gson gson;

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Before
    public void setUp() throws Exception {
        presenter = new CountryListPresenter(repository);
        presenter.takeView(view);


        gson = new Gson();
        updatedDbData = new MutableLiveData<>();
    }

    @Test
    public void loadNewData_successFetch_showSuccess() throws Exception {
        //given
        countryListResponse = gson.fromJson("[{\"iso\":\"AF\",\"name\":\"Afghanistan\",\"phone\":\"93\"},{\"iso\":\"AL\",\"name\":\"Albania\",\"phone\":\"355\"}]"
                , new TypeToken<List<CountryListModel>>() {
                }.getType());
        ((MutableLiveData) updatedDbData).setValue(countryListResponse);
        when(repository.getCountryList()).thenReturn(updatedDbData);
        //when
        presenter.reloadCountryList();
        //then
        verify(view).showLoading(true);
        verify(view).showCountryList(countryListResponse);
        verify(view).showMessage("Success");
        verify(view).showLoading(false);
    }

    @Test
    public void loadNewData_emptyDB_NoFetch_showFailed() throws Exception {
        //given
        countryListResponse = gson.fromJson("[]"
                , new TypeToken<List<CountryListModel>>() {
                }.getType());
        ((MutableLiveData) updatedDbData).setValue(countryListResponse);
        when(repository.getCountryList()).thenReturn(updatedDbData);
        //when
        presenter.reloadCountryList();
        //then
        verify(view).showLoading(true);
        verify(view).showMessage("Failed");
        verify(view).showLoading(false);
    }

}