package com.example.receptionmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.receptionmanager.activity.NewResident;
import com.example.receptionmanager.activity.ResidentDBActivity;
import com.example.receptionmanager.framents.NewResidentFragment;
import com.example.receptionmanager.framents.ResidentDBFragment;
import com.example.receptionmanager.pojos.Resident;
import com.example.receptionmanager.rec.ResidentRV;
import com.example.receptionmanager.vm.ResidentViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mAddNewResident;
    Button mResidentDB;

    FirebaseDatabase mDataBase;

    private static DatabaseReference ref;
    private static DatabaseReference refApp;
    private static ResidentViewModel viewModel;

    private static final Fragment[] fragments = new Fragment[]{new NewResidentFragment(),
            new ResidentDBFragment()};

    FragmentManager fm = getSupportFragmentManager();

    public static List<Resident> list = new ArrayList<>();

    Lifecycle lifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataBase = FirebaseDatabase.getInstance();

        ref = mDataBase.getReference("clients");
        refApp = mDataBase.getReference("apartment");

        mAddNewResident = findViewById(R.id.add_new_resident);
        mResidentDB = findViewById(R.id.resident_db);

        mAddNewResident.setOnClickListener(this);
        mResidentDB.setOnClickListener(this);

        MainLifecycle mainLifecycle = new MainLifecycle();
        lifecycle = getLifecycle();
        lifecycle.addObserver(mainLifecycle);

        viewModel = new ViewModelProvider(this).get(ResidentViewModel.class);
        viewModel.getResidents().observe(this, new Observer<List<Resident>>() {
            @Override
            public void onChanged(List<Resident> residents) {
                if (residents.size() == DataFromDB.count) {
                    list = residents;
               }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            switch (v.getId()) {
                case R.id.add_new_resident:
                    Intent intentNR = new Intent(this, NewResident.class);
                    startActivityForResult(intentNR, 1);
                    break;
                case R.id.resident_db:
                    Intent intentRDB = new Intent(this, ResidentDBActivity.class);
                    startActivityForResult(intentRDB, 2);
                    break;
            }
        } else {
            switch (v.getId()) {
                case R.id.add_new_resident:
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, fragments[0])
                            .commit();
                    break;
                case R.id.resident_db:
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, fragments[1])
                            .commit();
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragments[1])
                        .commit();
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragments[0])
                        .commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ConstraintLayout constraintLayout = findViewById(R.id.constraint);
        switch(id){
            case R.id.white:
                constraintLayout.setBackgroundColor(Color.WHITE);
                return true;
            case R.id.black:
                constraintLayout.setBackgroundColor(Color.GRAY);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Fragment[] getFragments() {
        return fragments;
    }

    public static DatabaseReference getRef() {
        return ref;
    }

    public static DatabaseReference getRefApp() {
        return refApp;
    }

    public static ResidentViewModel getViewModel() {
        return viewModel;
    }
}