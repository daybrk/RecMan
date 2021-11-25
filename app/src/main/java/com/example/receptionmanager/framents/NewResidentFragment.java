package com.example.receptionmanager.framents;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.receptionmanager.MainActivity;
import com.example.receptionmanager.R;
import com.example.receptionmanager.activity.SelectNumberActivity;
import com.example.receptionmanager.pojos.Resident;

import java.util.HashMap;

public class NewResidentFragment extends Fragment  implements View.OnClickListener {

    Button add;

    EditText edFio;
    EditText edPhone;
    EditText edStatus;
    EditText edPaymentStatus;
    EditText edPaymentCount;
    EditText edTermsOfStay;

    Button select;

    int id;
    String number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_new_resident, container, false);

        add = view.findViewById(R.id.btn_add);
        select = view.findViewById(R.id.but_select);

        add.setOnClickListener(this);
        select.setOnClickListener(this);

        edFio = view.findViewById(R.id.ed_fio);
        edPhone = view.findViewById(R.id.ed_phone);
        edPaymentStatus = view.findViewById(R.id.ed_payment_status);
        edPaymentCount = view.findViewById(R.id.ed_payment_count);
        edTermsOfStay = view.findViewById(R.id.ed_terms_stay);
        edStatus = view.findViewById(R.id.ed_status);

        return view;
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
                HashMap hashMap = new HashMap();
                hashMap.put("status", "busy");
                MainActivity.getRefApp().child(String.valueOf(id)).updateChildren(hashMap).addOnSuccessListener(o -> {
                });
                break;
            case R.id.but_select:
                Intent intent = new Intent(v.getContext(), SelectNumberActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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