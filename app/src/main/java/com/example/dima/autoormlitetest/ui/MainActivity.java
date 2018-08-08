package com.example.dima.autoormlitetest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.dima.autoormlitetest.R;
import com.example.dima.autoormlitetest.mvp.model.Car;
import com.example.dima.autoormlitetest.mvp.model.dao.HelperFactory;
import com.example.dima.autoormlitetest.mvp.presenter.ManufacturersPresenter;
import com.example.dima.autoormlitetest.mvp.view.ManufacturersView;
import com.example.dima.autoormlitetest.ui.adapter.ListCarsAdapter;

import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements ManufacturersView {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListCarsAdapter adapter;

    @InjectPresenter
    ManufacturersPresenter manufacturersPresenter;

    SwipeRefreshLayout swipeRefreshLayout;
    ImageButton searchButton;
    Spinner spinnerManufacture,spinnerModel;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                manufacturersPresenter.addFirstData();
            }
        });
        HelperFactory.setHelper(getApplicationContext());
        manufacturersPresenter.addFirstData();
        spinnerModel = findViewById(R.id.spinner_list_model);

        spinnerManufacture = findViewById(R.id.spinner_list_manufacture);//add to spinner nameManufacturers
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        manufacturersPresenter.getNameManufacturers());
        spinnerManufacture.setAdapter(arrayAdapter);
        spinnerManufacture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> nameOfModels = manufacturersPresenter.
                        getModelByManufacture((String) parent.getItemAtPosition(position));
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        getBaseContext(),
                        android.R.layout.simple_spinner_item,
                        nameOfModels);
                spinnerModel.setAdapter(arrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchButton = findViewById(R.id.button_find);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manufacturersPresenter.sortedByModel(spinnerModel.getSelectedItem().toString());
            }
        });
        //AdapterView.OnItemSelectedListener
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sorted_by_price:
                manufacturersPresenter.sortedByPrice();
                return true;
            case R.id.sorted_by_speed:
                manufacturersPresenter.sortedBySpeed();
                return true;
            case R.id.add_car:
                Intent intent = new Intent(this,CarActivity.class);
                intent.putExtra("nameOfModel",spinnerModel.getSelectedItem().toString());
                startActivity(intent);
                //manufacturersPresenter.addCar();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onDestroy() {
        HelperFactory.releaseHelper();
        super.onDestroy();
    }

    @Override
    public void onLoadResult(List<Car> cars) {
        adapter = new ListCarsAdapter(cars);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setRefreshing(boolean isRefresh) {
        swipeRefreshLayout.setRefreshing(isRefresh);
    }
}
