package com.example.dima.autoormlitetest.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dima.autoormlitetest.Interface.ItemClickListener;
import com.example.dima.autoormlitetest.R;
import com.example.dima.autoormlitetest.mvp.model.Car;
import com.example.dima.autoormlitetest.ui.CarActivity;

import java.util.List;

class ListCarsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ItemClickListener itemClickListener;

    TextView nameCar, speedCar, weightCar, priceCar,model;
    ImageView carImage;

    ListCarsViewHolder(View itemView) {
        super(itemView);
        nameCar = itemView.findViewById(R.id.car_name);
        priceCar = itemView.findViewById(R.id.car_price);
        speedCar = itemView.findViewById(R.id.car_speed);
        weightCar = itemView.findViewById(R.id.car_weight);
        carImage = itemView.findViewById(R.id.car_image);
        model = itemView.findViewById(R.id.model_of_car);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        try {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        } catch (NullPointerException e) {
        }
    }
}

public class ListCarsAdapter extends RecyclerView.Adapter<ListCarsViewHolder> {
    private List<Car> cars;

    public ListCarsAdapter(List<Car> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListCarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.car_card_layout, parent, false);
        return new ListCarsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListCarsViewHolder holder, int position) {
        holder.priceCar.setText(String.valueOf(cars.get(position).getPrice()));
        holder.nameCar.setText(cars.get(position).getNameCar());
        holder.speedCar.setText(String.valueOf(cars.get(position).getMaxSpeed()));
        holder.priceCar.setText(String.valueOf(cars.get(position).getPrice()));
        holder.weightCar.setText(String.valueOf(cars.get(position).getWeight()));
        holder.model.setText(cars.get(position).getCarModel().getName());
        //if (!cars.get(position).getPhotoId() == null) {
            Glide.with(holder.itemView.getContext())
                    .load(cars.get(position).getPhotoId())
                    .into(holder.carImage);
        //}

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(holder.itemView.getContext(), CarActivity.class);
                intent.putExtra("car",cars.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

}