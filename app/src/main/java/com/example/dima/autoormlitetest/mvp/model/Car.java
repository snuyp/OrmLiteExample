package com.example.dima.autoormlitetest.mvp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import static com.example.dima.autoormlitetest.mvp.model.Car.CAR_FIELD_NAME;

@DatabaseTable(tableName = CAR_FIELD_NAME)
public class Car implements Serializable{
    public final static String CAR_FIELD_NAME = "car";

    @DatabaseField(generatedId = true)
    private long autoId;
    @DatabaseField
    private long price;
    @DatabaseField
    private double weight;
    @DatabaseField
    private String nameCar;
    @DatabaseField
    private int maxSpeed;
    @DatabaseField
    private String photoId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private CarModel carModel;

    public Car() {
    }

    public Car(long price, double weight, String nameCar, int maxSpeed, String photoId) {
        this.price = price;
        this.weight = weight;
        this.nameCar = nameCar;
        this.maxSpeed = maxSpeed;
        this.photoId = photoId;
    }

    //without photoId
    public Car(long price, double weight, String nameCar, int maxSpeed, CarModel carModel) {
        this.price = price;
        this.weight = weight;
        this.nameCar = nameCar;
        this.maxSpeed = maxSpeed;
        this.carModel = carModel;
    }

    public Car(long price, double weight, String nameCar, int maxSpeed, String photoId, CarModel carModel) {
        this.price = price;
        this.weight = weight;
        this.nameCar = nameCar;
        this.maxSpeed = maxSpeed;
        this.photoId = photoId;
        this.carModel = carModel;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public long getAutoId() {
        return autoId;
    }

    public void setAutoId(long autoId) {
        this.autoId = autoId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
