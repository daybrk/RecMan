package com.example.receptionmanager.pojos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Resident")
public class Resident {

    @PrimaryKey(autoGenerate = true)
    int id;

    String fio;
    String phone;
    String status;
    String paymentStatus;
    String paymentCount;
    String termsOfStay;
    String apartment;

    public Resident(String fio, String phone, String status, String paymentStatus, String paymentCount, String termsOfStay, String apartment) {
        this.fio = fio;
        this.phone = phone;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.paymentCount = paymentCount;
        this.termsOfStay = termsOfStay;
        this.apartment = apartment;
    }

    public Resident() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(String paymentCount) {
        this.paymentCount = paymentCount;
    }

    public String getTermsOfStay() {
        return termsOfStay;
    }

    public void setTermsOfStay(String termsOfStay) {
        this.termsOfStay = termsOfStay;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
