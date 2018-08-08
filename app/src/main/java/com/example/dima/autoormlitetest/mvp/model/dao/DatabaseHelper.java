package com.example.dima.autoormlitetest.mvp.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dima.autoormlitetest.mvp.model.Car;
import com.example.dima.autoormlitetest.mvp.model.CarModel;
import com.example.dima.autoormlitetest.mvp.model.Manufacturers;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "myappname.db";
    private static final int DATABASE_VERSION = 1;

    private ManufactureDao manufactureDao = null;
    private CarModelDao carModelDao = null;
    private CarDao carDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Car.class);
            TableUtils.createTable(connectionSource, CarModel.class);
            TableUtils.createTable(connectionSource, Manufacturers.class);
        } catch (SQLException e) {
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Car.class, true);
            TableUtils.dropTable(connectionSource, CarModel.class, true);
            TableUtils.dropTable(connectionSource, Manufacturers.class, true);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ManufactureDao getManufacturersDAO() throws SQLException {
        if (manufactureDao == null) {
            manufactureDao = new ManufactureDao(getConnectionSource());
        }
        return manufactureDao;
    }

    public CarModelDao getCarModelDAO() throws SQLException {
        if (carModelDao == null) {
            carModelDao = new CarModelDao(getConnectionSource());
        }
        return carModelDao;
    }
    public CarDao getCarDAO() throws SQLException
    {
        if(carDao == null)
        {
            carDao = new CarDao(getConnectionSource());
        }
        return carDao;
    }

    @Override
    public void close() {
        super.close();
        manufactureDao = null;
        carModelDao = null;
    }
}
