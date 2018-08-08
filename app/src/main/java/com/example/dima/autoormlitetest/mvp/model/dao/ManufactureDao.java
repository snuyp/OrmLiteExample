package com.example.dima.autoormlitetest.mvp.model.dao;

import com.example.dima.autoormlitetest.mvp.model.Manufacturers;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class ManufactureDao extends BaseDaoImpl<Manufacturers,Long> {

    ManufactureDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource,Manufacturers.class);
    }

    public List<Manufacturers> findByName(String name) throws SQLException {
        return super.queryForEq("name", name);
    }
    public List<Manufacturers> getAll() throws SQLException {
        return this.queryForAll();
    }
}
