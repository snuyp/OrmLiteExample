package com.example.dima.autoormlitetest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.dima.autoormlitetest.mvp.model.Car;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CarView extends MvpView {
}
