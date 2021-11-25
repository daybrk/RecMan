package com.example.receptionmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.receptionmanager.DataFromDB;
import com.example.receptionmanager.MainActivity;
import com.example.receptionmanager.R;
import com.example.receptionmanager.framents.ResidentDBFragment;
import com.example.receptionmanager.pojos.Resident;
import com.example.receptionmanager.rec.ResidentRV;
import com.example.receptionmanager.vm.ResidentViewModel;

import java.util.List;

public class ResidentDBActivity extends AppCompatActivity implements View.OnClickListener {

    Button mAddNewResident;
    RecyclerView recyclerView;
    FragmentManager fm;

    ResidentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main);

            fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, MainActivity.getFragments()[1])
                    .commit();


            mAddNewResident = findViewById(R.id.add_new_resident);
            mAddNewResident.setOnClickListener(this);

        } else {
            setContentView(R.layout.activity_resident_d_b);

            recyclerView = findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            viewModel = new ViewModelProvider(this).get(ResidentViewModel.class);

            viewModel.getResidents().observe(this, new Observer<List<Resident>>() {
                @Override
                public void onChanged(List<Resident> residents) {
                    if (residents.size() == DataFromDB.count) {
                        recyclerView.setAdapter(new ResidentRV(residents));
                    }
                }
            });
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_new_resident) {
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

}