package com.example.currencyconversion;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurrencyDAO {
    @Insert
    void insert(CurrencyConversion currencyConversion);

    @Update
    void update(CurrencyConversion currencyConversion);

    @Query("SELECT * FROM currencies")
    List<CurrencyConversion> getAllCurrencies();

    @Query("SELECT * FROM currencies WHERE id = :id")
    CurrencyConversion getCurrencyByID(int id);
}
