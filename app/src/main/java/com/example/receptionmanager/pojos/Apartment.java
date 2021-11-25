package com.example.receptionmanager.pojos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Apartment")
public class Apartment {

    @PrimaryKey()
    int id;

    String number;

    String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
