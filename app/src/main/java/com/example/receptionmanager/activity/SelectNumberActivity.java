package com.example.receptionmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.receptionmanager.DataFromDB;
import com.example.receptionmanager.MainActivity;
import com.example.receptionmanager.R;
import com.example.receptionmanager.pojos.Apartment;
import com.example.receptionmanager.pojos.Resident;
import com.example.receptionmanager.rec.ApartmentRV;
import com.example.receptionmanager.vm.ResidentViewModel;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;
import java.util.List;

public class SelectNumberActivity extends AppCompatActivity {

    RecyclerView recycler;
    private static ResidentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number);

        DataFromDB.getApartmentFromDB();

        SelectNumberActivity selectNumberActivity = this;

        recycler = findViewById(R.id.recyclerSNA);
        recycler.setLayoutManager(new GridLayoutManager(this, 4));
        viewModel = new ViewModelProvider(this).get(ResidentViewModel.class);
        viewModel.getApartment().observe(this, new Observer<List<Apartment>>() {
            @Override
            public void onChanged(List<Apartment> apartments) {
                if (apartments.size() == DataFromDB.countApp) {
                    recycler.setAdapter(new ApartmentRV(apartments, selectNumberActivity));
                }
            }
        });
    }


    public void finishActivity(Intent intent) {

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}