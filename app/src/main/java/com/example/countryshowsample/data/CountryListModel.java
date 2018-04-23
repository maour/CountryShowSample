package com.example.countryshowsample.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = Config.Country_Table_Name)
public class CountryListModel {

    @SerializedName("iso")
    @Expose
    private String iso;
    @SerializedName("name")
    @Expose
    private String name;
    @PrimaryKey
    @SerializedName("phone")
    @Expose
    @NonNull
    private String phone;

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}