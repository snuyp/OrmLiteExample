package com.example.dima.autoormlitetest.mvp.model;

import com.example.dima.autoormlitetest.mvp.model.dao.HelperFactory;
import com.example.dima.autoormlitetest.mvp.model.dao.ManufactureDao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.sql.SQLException;

import static com.example.dima.autoormlitetest.mvp.model.Manufacturers.MANUFACTURE_FIELD_NAME;

@DatabaseTable(tableName = MANUFACTURE_FIELD_NAME, daoClass = ManufactureDao.class)
public class Manufacturers implements Serializable{
    public final static String MANUFACTURE_FIELD_NAME = "manufacture";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private String name;

    @ForeignCollectionField(eager = true) //one-to-many relationship
    private ForeignCollection<CarModel> carModels;

    public Manufacturers() {
    }

    public ForeignCollection<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(ForeignCollection<CarModel> carModels) {
        this.carModels = carModels;
    }

    public Manufacturers(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
/*
БД:

+ БД содержит таблицы: автомобили (цена, 5-7 полей с характеристиками, желательно с фото), производители автомобилей, марки автомобилей. Таблицы взаимосвязаны.

+ БД содержит 12 автомобилей, 3 производителя, 1-3 марки у каждого производителя

Приложение, должно иметь следующие функции:

+ отображение списка автомобилей с характеристиками

- фильтрация по производителю и марке

+ сортировка по цене

- добавление нового автомобиля

+ редактирование деталей автомобиля*/