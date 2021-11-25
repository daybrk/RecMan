package com.example.receptionmanager.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.receptionmanager.pojos.Apartment;
import com.example.receptionmanager.pojos.Resident;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Resident.class, Apartment.class}, version = 1, exportSchema = false)
public abstract class ResidentDB extends RoomDatabase {
    public abstract ResidentDAO residentDAO();

    private static volatile ResidentDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dbWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ResidentDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ResidentDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ResidentDB.class, "database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
