package com.example.dima.autoormlitetest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.example.dima.autoormlitetest.mvp.model.Car;

import java.util.List;

public interface ManufacturersView extends MvpView {
    void onLoadResult(List<Car> cars);
    void setRefreshing(boolean isRefresh);

}
