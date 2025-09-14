package com.example.currencyconversion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Database;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button convert, cur1, cur2;
    EditText et;
    TextView end;
    CurrencyDatabase database;
    CurrencyDAO dao;
    CurrencyConversion NIStoUSD, NIStoPND, USDtoPND, USDtoNIS, PNDtoUSD, PNDtoNIS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        database = CurrencyDatabase.getInstance(this);
        dao = database.dao();

        /*NIStoPND = new CurrencyConversion(0.22, "Israel", "The UK");
        NIStoUSD = new CurrencyConversion(0.3, "Israel", "USA");
        USDtoPND = new CurrencyConversion(0.74, "USA", "The UK");*/
        USDtoNIS = new CurrencyConversion(3.32, "USA", "Israel");
        PNDtoNIS = new CurrencyConversion(4.52, "The UK", "Israel");
        PNDtoUSD = new CurrencyConversion(1.36, "The UK", "USA");

        dao.insert(USDtoNIS); //ID:1
        dao.insert(PNDtoNIS); //ID:2
        dao.insert(PNDtoUSD); //ID:3

        convert = findViewById(R.id.button);
        cur1 = findViewById(R.id.cur1);
        cur2 = findViewById(R.id.cur2);
        et = findViewById(R.id.rndNum);
        end = findViewById(R.id.endRes);
        String[] currencies={"NIS","USD","Pound Sterling"};
        boolean[] checkedCurrencies = new boolean[]{false,false, false};

        DialogInterface.OnMultiChoiceClickListener listener = new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                checkedCurrencies[i] = b;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        cur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Choose one currency")
                        .setMultiChoiceItems(currencies, checkedCurrencies, listener)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                i = 0;
                                int index = 0;
                                for(boolean bool : checkedCurrencies){
                                    if(bool){
                                        index = i;
                                        i++;
                                    }
                                }
                                cur1.setText(currencies[index]);
                            }
                        });
                builder.create().show();
            }
        });

        cur2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Choose one currency")
                        .setMultiChoiceItems(currencies, checkedCurrencies, listener)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                i = 0;
                                int index = 0;
                                for(boolean bool : checkedCurrencies){
                                    if(bool){
                                        index = i;
                                        i++;
                                    }
                                }
                                cur2.setText(currencies[index]);
                            }
                        });
                builder.create().show();
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cur1.getText() == "NIS" && cur2.getText() == "USD"){
                    end.setText((double)temp/dao.getCurrencyByID(1).getAmount());
                }
                else if(cur1.getText() == "NIS" && cur2.getText() == "PND"){

                }
                else if(cur1.getText() == "USD" && cur2.getText() == "PND"){

                }
            }
        });

    }
}