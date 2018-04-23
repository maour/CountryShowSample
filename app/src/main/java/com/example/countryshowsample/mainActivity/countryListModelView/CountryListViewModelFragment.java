package com.example.countryshowsample.mainActivity.countryListModelView;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.countryshowsample.R;
import com.example.countryshowsample.di.PerActivity;
import com.example.countryshowsample.mainActivity.countryListModelView.adapter.CountryListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

@PerActivity
public class CountryListViewModelFragment extends DaggerFragment {

    @BindView(R.id.countryListRV)
    RecyclerView countryListRV;

    @Inject
    CountryListVMFactory vmFactory;

    CountryListViewModel countryListViewModel;

    private CountryListAdapter countriesAdapter;

    @Inject
    public CountryListViewModelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        countryListRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        countriesAdapter = new CountryListAdapter();
        countryListRV.setAdapter(countriesAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        countryListViewModel = ViewModelProviders.of(this, vmFactory).get(CountryListViewModel.class);
        countryListViewModel.countryListResponse()
                .observe(this,
                        countryListModels -> countriesAdapter.updateModel(countryListModels)
                );
        countryListViewModel.reloadCountryList();
    }

}
