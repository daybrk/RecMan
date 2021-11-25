package com.example.receptionmanager.rec;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receptionmanager.R;
import com.example.receptionmanager.pojos.Resident;

import java.util.List;

public class ResidentRV extends RecyclerView.Adapter<ResidentRV.ViewHolder> {

    final List<Resident> mValues;

    public ResidentRV(List<Resident> mValues) {
        this.mValues = mValues;
    }

    @NonNull
    @Override
    public ResidentRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resident_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResidentRV.ViewHolder holder, int position) {
        holder.fio.setText(mValues.get(position).getFio());
        holder.phone.setText(mValues.get(position).getPhone());
        holder.status.setText(mValues.get(position).getStatus());
        holder.paymentStatus.setText(mValues.get(position).getPaymentStatus());
        holder.paymentCount.setText(mValues.get(position).getPaymentCount());
        holder.termsOfStay.setText(mValues.get(position).getTermsOfStay());
        holder.apartment.setText(mValues.get(position).getApartment());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView fio;
        TextView phone;
        TextView status;
        TextView paymentStatus;
        TextView paymentCount;
        TextView termsOfStay;
        TextView apartment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fio = itemView.findViewById(R.id.item_tv_fio);
            phone = itemView.findViewById(R.id.item_tv_phone);
            status = itemView.findViewById(R.id.item_tv_status);
            paymentStatus = itemView.findViewById(R.id.item_tv_payment_status);
            paymentCount = itemView.findViewById(R.id.item_tv_payment_count);
            termsOfStay = itemView.findViewById(R.id.item_tv_terms_of_stay);
            apartment = itemView.findViewById(R.id.item_tv_apartment);

        }
    }
}
