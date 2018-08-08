package com.example.dima.autoormlitetest.mvp.model;

import com.example.dima.autoormlitetest.mvp.model.Car;
import com.example.dima.autoormlitetest.mvp.model.dao.HelperFactory;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.sql.SQLException;

import static com.example.dima.autoormlitetest.mvp.model.CarModel.MODELS_FIELD_NAME;

@DatabaseTable(tableName = MODELS_FIELD_NAME)
public class CarModel implements Serializable{
    public final static String MODELS_FIELD_NAME = "models";

    @DatabaseField(generatedId = true)
    private long modelsId;

    @DatabaseField(canBeNull = false)
    private String name;

    @ForeignCollectionField(eager = true) //one-to-many relationship
    private ForeignCollection<Car> cars;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private Manufacturers manufacturers;

    public CarModel() {
    }

    public CarModel(String name) {
        this.name = name;
    }

    public Manufacturers getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(Manufacturers manufacturers) {
        this.manufacturers = manufacturers;
    }

    public long getModelsId() {
        return modelsId;
    }

    public void setModelsId(long modelsId) {
        this.modelsId = modelsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Car> getCars() {
        return cars;
    }

    public void setCars(ForeignCollection<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) throws SQLException {
        car.setCarModel(this);
        HelperFactory.getHelper().getCarDAO().create(car);
       // cars.add(car);
    }
}
