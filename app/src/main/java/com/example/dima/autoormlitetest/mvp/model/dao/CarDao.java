package com.example.dima.autoormlitetest.mvp.model.dao;

import com.example.dima.autoormlitetest.mvp.model.Car;
import com.example.dima.autoormlitetest.mvp.model.CarModel;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class CarDao extends BaseDaoImpl<Car,Long> {
    public CarDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Car.class);
    }
    public List<Car> findByName(String name) throws SQLException {
        return super.queryForEq("name", name);
    }
    public List<Car> getAll() throws SQLException {
        return this.queryForAll();
    }
    public List<Car> getSortedByPrice() throws SQLException {
        QueryBuilder<Car, Long> queryBuilder = queryBuilder();
        queryBuilder.groupBy("price");
        return queryBuilder.query();
    }

    public List<Car> getSortedBySpeed() throws SQLException {
        QueryBuilder<Car, Long> queryBuilder = queryBuilder();
        queryBuilder.groupBy("maxSpeed");
        return queryBuilder.query();
    }


}
