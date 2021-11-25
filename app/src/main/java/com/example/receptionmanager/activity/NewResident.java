package com.example.receptionmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.receptionmanager.MainActivity;
import com.example.receptionmanager.R;
import com.example.receptionmanager.pojos.Resident;

import java.util.HashMap;

public class NewResident extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fm;
    
    Button add;
    Button select;

    EditText edFio;
    EditText edPhone;
    EditText edStatus;
    EditText edPaymentStatus;
    EditText edPaymentCount;
    EditText edTermsOfStay;

    Button mResidentDB;

    int id;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main);

            fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, MainActivity.getFragments()[0])
                    .commit();

            mResidentDB = findViewById(R.id.resident_db);
            mResidentDB.setOnClickListener(this);

        } else {
            setContentView(R.layout.activity_new_resident);

            add = findViewById(R.id.btn_add);
            select = findViewById(R.id.but_select);

            add.setOnClickListener(this);
            select.setOnClickListener(this);

            edFio = findViewById(R.id.ed_fio);
            edPhone = findViewById(R.id.ed_phone);
            edPaymentStatus = findViewById(R.id.ed_payment_status);
            edPaymentCount = findViewById(R.id.ed_payment_count);
            edTermsOfStay = findViewById(R.id.ed_terms_stay);
            edStatus = findViewById(R.id.ed_status);

        }


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                Resident newResident = new Resident(String.valueOf(edFio.getText()), String.valueOf(edPhone.getText()),
                        String.valueOf(edStatus.getText()), String.valueOf(edPaymentStatus.getText()),
                        String.valueOf(edPaymentCount.getText()), String.valueOf(edTermsOfStay.getText()), number);
                MainActivity.getRef().push().setValue(newResident);
                Toast toast = Toast.makeText(v.getContext(), "Клиент был добавлен в базу данных", Toast.LENGTH_SHORT);
                toast.show();
                setResult(Activity.RESULT_FIRST_USER);
                HashMap hashMap = new HashMap();
                hashMap.put("status", "busy");
                MainActivity.getRefApp().child(String.valueOf(id)).updateChildren(hashMap).addOnSuccessListener(o -> {
                });
                finish();
                break;
            case R.id.but_select:
                Intent intent = new Intent(this, SelectNumberActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.resident_db:
                setResult(Activity.RESULT_OK);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {


                id = data.getIntExtra("Id", 0);
                number = data.getStringExtra("Number");

                select.setText(data.getStringExtra("Number"));
            }
        }
    }
}