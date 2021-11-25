package com.example.receptionmanager.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.receptionmanager.pojos.Apartment;
import com.example.receptionmanager.pojos.Resident;

import java.util.List;

@Dao
public interface ResidentDAO {


    @Query("SELECT * FROM Resident")
    LiveData<List<Resident>> loadAllResident();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResident (Resident resident);

    @Query("DELETE FROM Resident")
    void deleteResidents();


    @Query("SELECT * FROM Apartment")
    LiveData<List<Apartment>> loadAllApartments();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertApartment (Apartment apartment);

    @Query("DELETE FROM Apartment")
    void deleteApartment();
}
