package com.example.dima.autoormlitetest.mvp.model.dao;

import com.example.dima.autoormlitetest.mvp.model.Car;
import com.example.dima.autoormlitetest.mvp.model.CarModel;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class CarModelDao extends BaseDaoImpl<CarModel,Long> {
    public CarModelDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, CarModel.class);
    }
    public List<CarModel> findByName(String name) throws SQLException {
        return super.queryForEq("name", name);
    }
    public List<CarModel> getAll() throws SQLException {
        return this.queryForAll();
    }
}
