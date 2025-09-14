package com.example.currencyconversion;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "currencies")
public class CurrencyConversion {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private double amount;
    private String originCountry;
    private String destCountry;

    public CurrencyConversion(double amount, String originCountry, String destCountry) {
        this.amount = amount;
        this.originCountry = originCountry;
        this.destCountry = destCountry;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOriginCountry() {
        return this.originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getDestCountry() {
        return this.destCountry;
    }

    public void setDestCountry(String destCountry) {
        this.destCountry = destCountry;
    }
}
