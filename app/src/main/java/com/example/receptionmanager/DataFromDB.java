package com.example.receptionmanager;

import androidx.annotation.NonNull;

import com.example.receptionmanager.activity.ResidentDBActivity;
import com.example.receptionmanager.pojos.Apartment;
import com.example.receptionmanager.pojos.Resident;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class DataFromDB {

    public static int count = 0;
    public static int countApp = 0;

    public static void getDataFromDB() {

        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MainActivity.getViewModel().deleteReviewConsistent();
                count = 0;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    addDataToRoomDB(ds);
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        MainActivity.getRef().addValueEventListener(vListener);
    }


    private static void addDataToRoomDB(DataSnapshot ds) {
        Resident resident = new Resident();
        resident.setFio(String.valueOf(ds.child("fio").getValue()));
        resident.setPhone(String.valueOf(ds.child("phone").getValue()));
        resident.setStatus(String.valueOf(ds.child("status").getValue()));
        resident.setPaymentStatus(String.valueOf(ds.child("paymentStatus").getValue()));
        resident.setPaymentCount(String.valueOf(ds.child("paymentCount").getValue()));
        resident.setTermsOfStay(String.valueOf(ds.child("termsOfStay").getValue()));
        resident.setApartment(String.valueOf(ds.child("apartment").getValue()));
        MainActivity.getViewModel().insertReviewConsistent(resident);
    }

    public static void getApartmentFromDB() {

        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MainActivity.getViewModel().deleteApartment();
                countApp = 0;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    addApartmentToRoomDB(ds);
                    countApp++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        MainActivity.getRefApp().addValueEventListener(vListener);
    }


    private static void addApartmentToRoomDB(DataSnapshot ds) {
        Apartment apartment = new Apartment();
        apartment.setId(Integer.parseInt(ds.getKey()));
        apartment.setNumber(String.valueOf(ds.child("number").getValue()));
        apartment.setStatus(String.valueOf(ds.child("status").getValue()));
        MainActivity.getViewModel().insertApartment(apartment);
    }

}
