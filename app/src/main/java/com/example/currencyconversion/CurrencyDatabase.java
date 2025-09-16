package com.example.currencyconversion;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CurrencyConversion.class}, version = 1)
public abstract class CurrencyDatabase extends RoomDatabase {
    private static CurrencyDatabase instance;

    public abstract CurrencyDAO dao();

    public static synchronized CurrencyDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                            CurrencyDatabase.class,
                    "currency_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
