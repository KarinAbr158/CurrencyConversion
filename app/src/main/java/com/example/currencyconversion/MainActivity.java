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
    int checkedCurrencyIndex, intValue;
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

        convert = findViewById(R.id.button);
        cur1 = findViewById(R.id.cur1);
        cur2 = findViewById(R.id.cur2);
        et = findViewById(R.id.rndNum);
        end = findViewById(R.id.endRes);
        String[] currencies={"NIS","USD","Pound Sterling"};
        checkedCurrencyIndex = 0;

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                checkedCurrencyIndex = i;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        cur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Choose one currency")
                        .setSingleChoiceItems(currencies, checkedCurrencyIndex, listener)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cur1.setText(currencies[checkedCurrencyIndex]);
                            }
                        });
                builder.create().show();
            }
        });

        cur2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Choose one currency")
                        .setSingleChoiceItems(currencies, checkedCurrencyIndex, listener)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                cur2.setText(currencies[checkedCurrencyIndex]);
                            }
                        });
                builder.create().show();
            }
        });


        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intValue = Integer.parseInt(et.getText().toString());
                if(cur1.getText().toString().equals("NIS") && cur2.getText().toString().equals("USD")){
                    end.setText(""+intValue/3);
                }
                else if(cur1.getText() == "NIS" && cur2.getText() == "Pound Sterling"){
                    end.setText(""+intValue/5);
                }
                else if(cur1.getText() == "USD" && cur2.getText() == "Pound Sterling"){
                    end.setText(""+intValue/2);
                }
                else if(cur1.getText() == "USD" && cur2.getText() == "NIS"){
                    end.setText(""+intValue*3);
                }
                else if(cur1.getText() == "Pound Sterling" && cur2.getText() == "NIS"){
                    end.setText(""+intValue*5);
                }
                else if(cur1.getText() == "Pound Sterling" && cur2.getText() == "USD"){
                    end.setText(""+intValue*2);
                }
            }
        });

    }
}