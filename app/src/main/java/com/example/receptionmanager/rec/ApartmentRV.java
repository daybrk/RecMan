package com.example.receptionmanager.rec;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receptionmanager.R;
import com.example.receptionmanager.activity.SelectNumberActivity;
import com.example.receptionmanager.pojos.Apartment;

import java.util.List;

public class ApartmentRV extends RecyclerView.Adapter<ApartmentRV.ViewHolder> {

    private final List<Apartment> mValues;
    SelectNumberActivity select;

    public ApartmentRV(List<Apartment> mValues, SelectNumberActivity select) {
        this.select = select;
        this.mValues = mValues;
    }


    @NonNull
    @Override
    public ApartmentRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_number_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentRV.ViewHolder holder, int position) {

        if (mValues.get(position).getStatus().equals("busy")) {
            holder.apartment.setBackgroundColor(Color.parseColor("#DA2C2C"));
        } else {
            holder.apartment.setBackgroundColor(Color.parseColor("#44DA2C"));
            holder.apartment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("Id", mValues.get(position).getId());
                    intent.putExtra("Number", mValues.get(position).getNumber());

                    select.finishActivity(intent);
                }
            });
        }
        holder.apartment.setText(mValues.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Button apartment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            apartment = itemView.findViewById(R.id.but_apartment);
        }
    }
}
