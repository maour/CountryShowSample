package com.example.countryshowsample.mainActivity.countryListMVP.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.countryshowsample.R;
import com.example.countryshowsample.data.CountryListModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE = 0;
    private static final int HEADER_TYPE = 1;
    private static final int FOOTER_TYPE = 2;

    private List<CountryListModel> myCountriesModel;

    public CountryListAdapter() {
    }

    public void updateModel(List<CountryListModel> model) {
        myCountriesModel = model;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER_TYPE:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.header_row, parent, false));
            case FOOTER_TYPE:
                return new FooterViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.footer_row, parent, false));
            default:
                return new MyViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.country_detail_row, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER_TYPE;
        else if (position == myCountriesModel.size() + 1)
            return FOOTER_TYPE;
        else
            return ITEM_TYPE;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.name.setText(myCountriesModel.get(position - 1).getName());
            myViewHolder.phone.setText(myCountriesModel.get(position - 1).getPhone());
            myViewHolder.iso.setText(myCountriesModel.get(position - 1).getIso());
        }
    }

    @Override
    public int getItemCount() {
        return myCountriesModel != null ? myCountriesModel.size() + 2 : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.countryNameTV)
        TextView name;
        @BindView(R.id.countryPhoneTV)
        TextView phone;
        @BindView(R.id.countryIsoTV)
        TextView iso;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}

