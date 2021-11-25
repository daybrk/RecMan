package com.example.receptionmanager.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.receptionmanager.pojos.Apartment;
import com.example.receptionmanager.pojos.Resident;

import java.util.List;

public class ResidentViewModel extends AndroidViewModel {

    Repository repository;
    private final LiveData<List<Resident>> residents;
    private final LiveData<List<Apartment>> apartment;


    public ResidentViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);

        residents = repository.getResident();
        apartment = repository.getApartment();
    }

    public LiveData<List<Resident>> getResidents() {
        return residents;
    }

    public LiveData<List<Apartment>> getApartment() {
        return apartment;
    }

    public void insertReviewConsistent (Resident resident) {
        repository.insertResident(resident);
    }

    public void insertApartment (Apartment apartment) {
        repository.insertApartment(apartment);
    }

    public void deleteReviewConsistent() {
        repository.deleteResidents();
    }

    public void deleteApartment() {
        repository.deleteApartment();
    }

}
