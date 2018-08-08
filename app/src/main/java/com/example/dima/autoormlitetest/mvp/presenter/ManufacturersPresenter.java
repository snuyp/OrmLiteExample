package com.example.dima.autoormlitetest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dima.autoormlitetest.mvp.model.Car;
import com.example.dima.autoormlitetest.mvp.model.dao.HelperFactory;
import com.example.dima.autoormlitetest.mvp.model.CarModel;
import com.example.dima.autoormlitetest.mvp.model.Manufacturers;
import com.example.dima.autoormlitetest.mvp.view.ManufacturersView;
import com.j256.ormlite.dao.ForeignCollection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@InjectViewState
public class ManufacturersPresenter extends MvpPresenter<ManufacturersView> {
    private Random rnd = new Random();

    public void addFirstData() {
        try {
            if (HelperFactory.getHelper().getManufacturersDAO().getAll().isEmpty()) {
                List<Manufacturers> s = HelperFactory.getHelper().getManufacturersDAO().getAll();
                //for example
                addManufacturers("Tin Cuan", "Mazda");
                addManufacturers("Second Lian", "Honda,Nissan");
                addManufacturers("Min Huan", "BMW,Jaguar,Fiat");
                autoAddCar();

                List<Car> cars = HelperFactory.getHelper().getCarDAO().getAll();
                getViewState().onLoadResult(cars);
            } else {
                getViewState().onLoadResult(HelperFactory.getHelper().getCarDAO().getAll());
                getViewState().setRefreshing(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void autoAddCar() {
        try {
            List<CarModel> carModels = HelperFactory.getHelper().getCarModelDAO().getAll();
            for (CarModel carModel : carModels) {
                carModel.getCars().add(
                        new Car(rnd.nextInt(1000) + 1000, 2.2, "A" + rnd.nextInt(10), rnd.nextInt(100) + 100, ""));
                carModel.getCars().add(
                        new Car(rnd.nextInt(1000) + 1000, 2, "B" + rnd.nextInt(10), rnd.nextInt(100) + 100, ""));
                carModel.getCars().add(
                        new Car(rnd.nextInt(1000) + 1000, 1.4, "C" + rnd.nextInt(10), rnd.nextInt(100) + 100, ""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getNameManufacturers() {
        List<Manufacturers> manufacturers = null;
        List<String> names = new ArrayList<>();
        try {
            manufacturers = HelperFactory.getHelper().getManufacturersDAO().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Manufacturers m : manufacturers) {
            names.add(m.getName());
        }
        return names;
    }



    private void addManufacturers(String nameManufacturers, String carModels) {
        try {
            String[] splitCar = carModels.split(",");
            Manufacturers manufacturers = new Manufacturers();
            manufacturers.setName(nameManufacturers);
            HelperFactory.getHelper().getManufacturersDAO().create(manufacturers);
            HelperFactory.getHelper().getManufacturersDAO().refresh(manufacturers);
            for (String models : splitCar) {
                manufacturers.getCarModels().add(new CarModel(models));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sortedByPrice() {
        try {
            getViewState().onLoadResult(HelperFactory.getHelper().getCarDAO().getSortedByPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sortedBySpeed() {
        try {
            getViewState().onLoadResult(HelperFactory.getHelper().getCarDAO().getSortedBySpeed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getModelByManufacture(String name) {
        try {
            ForeignCollection<CarModel> carModels = HelperFactory.getHelper().getManufacturersDAO().findByName(name).get(0).getCarModels();
            return getNameOfModels(carModels);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getNameOfModels(ForeignCollection<CarModel> carModels) {
        List<String> name = new ArrayList<>();
        for (CarModel model : carModels) {
            name.add(model.getName());
        }
        return name;
    }

    public void sortedByModel(String nameOfModel) {
        List<Car> cars = new ArrayList<>();
        try {
            ForeignCollection<Car> carsForeign = HelperFactory.getHelper().getCarModelDAO().findByName(nameOfModel).get(0).getCars();
            cars.addAll(carsForeign);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getViewState().onLoadResult(cars);
    }

}
