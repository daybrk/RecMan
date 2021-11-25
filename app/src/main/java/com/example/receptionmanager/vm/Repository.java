package com.example.receptionmanager.vm;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.receptionmanager.db.ResidentDAO;
import com.example.receptionmanager.db.ResidentDB;
import com.example.receptionmanager.pojos.Apartment;
import com.example.receptionmanager.pojos.Resident;

import java.util.List;

public class Repository {

    LiveData<List<Resident>> resident;
    LiveData<List<Apartment>> apartment;
    ResidentDAO dao;

    public Repository(Application application) {
        ResidentDB rd = ResidentDB.getDatabase(application);

        dao = rd.residentDAO();

        apartment = dao.loadAllApartments();
        resident = dao.loadAllResident();

    }

    public LiveData<List<Resident>> getResident() {
        return resident;
    }

    public LiveData<List<Apartment>> getApartment() {
        return apartment;
    }

    void insertResident (Resident resident) {
        ResidentDB.dbWriteExecutor.execute(() -> dao.insertResident(resident));
    }

    void insertApartment (Apartment apartment) {
        ResidentDB.dbWriteExecutor.execute(() -> dao.insertApartment(apartment));
    }

    void deleteResidents() {
        dao.deleteResidents();
    }

    void deleteApartment() {
        dao.deleteApartment();
    }

}
