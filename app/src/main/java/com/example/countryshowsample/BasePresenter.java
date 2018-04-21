package com.example.countryshowsample;


public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();
}
