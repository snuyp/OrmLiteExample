package com.example.dima.autoormlitetest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dima.autoormlitetest.mvp.model.Car;
import com.example.dima.autoormlitetest.mvp.model.dao.HelperFactory;
import com.example.dima.autoormlitetest.mvp.view.CarView;

import java.sql.SQLException;

@InjectViewState
public class CarPresenter extends MvpPresenter<CarView>{

}
