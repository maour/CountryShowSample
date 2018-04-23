package com.example.countryshowsample.mainActivity.countryList;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.countryshowsample.R;
import com.example.countryshowsample.data.CountryListModel;
import com.example.countryshowsample.di.PerActivity;
import com.example.countryshowsample.mainActivity.countryList.adapter.CountryListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

@PerActivity
public class CountryListFragment extends DaggerFragment implements CountryListContract.View {

    @BindView(R.id.countryListRV)
    RecyclerView countryListRV;

    @Inject
    CountryListContract.Presenter presenter;
    private CountryListAdapter countriesAdapter;

    @Inject
    public CountryListFragment() {
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
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
        presenter.reloadCountryList();
    }

    @Override
    public void onPause() {
        presenter.dropView();
        super.onPause();
    }

    @Override
    public void showLoading(boolean show) {
        //implement show loading in future
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCountryList(List<CountryListModel> countryListResponse) {
        countriesAdapter.updateModel(countryListResponse);
    }
}
