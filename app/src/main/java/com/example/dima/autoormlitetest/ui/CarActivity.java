package com.example.dima.autoormlitetest.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.dima.autoormlitetest.R;
import com.example.dima.autoormlitetest.mvp.model.Car;
import com.example.dima.autoormlitetest.mvp.model.dao.HelperFactory;
import com.example.dima.autoormlitetest.mvp.presenter.CarPresenter;
import com.example.dima.autoormlitetest.mvp.view.CarView;

import java.sql.SQLException;

public class CarActivity extends MvpAppCompatActivity implements CarView {
    @InjectPresenter
    CarPresenter carPresenter;
    EditText edit_speed, edit_weight, edit_name, edit_price;
    Button ok_button;
    Car car;
    TextView name_of_model;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_activity);
        TextView textView = findViewById(R.id.car_id);
        //carPresenter.editCar(car);
        edit_price = findViewById(R.id.edit_car_price);
        edit_speed = findViewById(R.id.edit_car_speed);
        edit_weight = findViewById(R.id.edit_car_weight);
        edit_name = findViewById(R.id.edit_car_name);

        if (getIntent().getExtras().get("car") != null) {
            car = (Car) getIntent().getExtras().get("car");
            edit_name.setText(car.getNameCar());
            edit_speed.setText(String.valueOf(car.getMaxSpeed()));
            edit_price.setText(String.valueOf(car.getPrice()));
            edit_weight.setText(String.valueOf(car.getWeight()));
            textView.setText(String.valueOf(car.getAutoId()));
        } else {
            car = new Car();
        }

        ok_button = findViewById(R.id.button_car_edit);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (getIntent().getExtras().get("car") != null) {
                        HelperFactory.getHelper().getCarDAO().update(car);
                    } else {
                        HelperFactory.getHelper()
                                .getCarModelDAO()
                                .findByName
                                        (getIntent().getStringExtra("nameOfModel")).get(0).addCar(car);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                finish();
                Toast.makeText(CarActivity.this, "Car was edit", Toast.LENGTH_SHORT).show();
            }
        });

        edit_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null)
                    car.setNameCar(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    car.setPrice(Long.parseLong((s.toString())));
                } catch (NumberFormatException n) {
                    Toast.makeText(CarActivity.this, "NumberFormatExxception", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_speed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    car.setMaxSpeed(Integer.parseInt(s.toString()));
                } catch (NumberFormatException n) {
                    Toast.makeText(CarActivity.this, "NumberFormatException", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edit_weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    car.setWeight(Double.parseDouble(s.toString()));
                } catch (NumberFormatException n) {
                    Toast.makeText(CarActivity.this, "NumberFormatException", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
