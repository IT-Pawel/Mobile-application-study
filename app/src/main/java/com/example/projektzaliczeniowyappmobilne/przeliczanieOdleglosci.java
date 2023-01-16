package com.example.projektzaliczeniowyappmobilne;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class przeliczanieOdleglosci extends AppCompatActivity {
// http://www.elendilion.pl/2021/03/02/hobbickie-miary-dlugosci/
    Button goHome, przelicz;
    Spinner jednostkaBazowa, jednostkaKoncowa;

    EditText wartoscBazowa, wartoscKoncowa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przeliczanie_odleglosci);
        goHome = findViewById(R.id.home);
        przelicz = findViewById(R.id.przeliczWartosci);
        wartoscBazowa = (EditText) findViewById(R.id.bazowaWartosc);
        wartoscKoncowa = (EditText) findViewById(R.id.koncowaWartosc);
        jednostkaBazowa =  findViewById(R.id.jednostkaBazowa);
        jednostkaKoncowa =  findViewById(R.id.jednostkaKoncowa);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
        androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.jednostkiOdleglosci) );
        adapter.notifyDataSetChanged();
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        jednostkaBazowa.setAdapter(adapter);
        jednostkaKoncowa.setAdapter(adapter);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        przelicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wartoscBazowa.getText().toString().matches("")){
                    final String snack = "Wartość jest pusta";
                    Snackbar.make(view, snack, Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(Float.valueOf(wartoscBazowa.getText().toString()) <=0 ){
                    final String snack = "Wartość nie może być mniejsza od 0";
                    Snackbar.make(view, snack, Snackbar.LENGTH_LONG).show();
                    return;
                }

                float wynik = przeliczWartosc(Float.valueOf(wartoscBazowa.getText().toString()), jednostkaBazowa.getSelectedItem().toString(), jednostkaKoncowa.getSelectedItem().toString());
                DecimalFormat df = new DecimalFormat("0.00");
                String zaokroglenie = df.format(wynik);
                wartoscKoncowa.setText(zaokroglenie);

                jednostkaBazowa.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                        float wynik = przeliczWartosc(Float.valueOf(wartoscBazowa.getText().toString()), jednostkaBazowa.getSelectedItem().toString(), jednostkaKoncowa.getSelectedItem().toString());
                        DecimalFormat df = new DecimalFormat("0.00");
                        String zaokroglenie = df.format(wynik);
                        wartoscKoncowa.setText(zaokroglenie);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                jednostkaKoncowa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        float wynik = przeliczWartosc(Float.valueOf(wartoscKoncowa.getText().toString()), jednostkaKoncowa.getSelectedItem().toString(), jednostkaBazowa.getSelectedItem().toString());
                        DecimalFormat df = new DecimalFormat("0.00");
                        String zaokroglenie = df.format(wynik);
                        wartoscBazowa.setText(zaokroglenie);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
    }

    private float przeliczWartosc(float wartosc, String jednostkaPoczatkowaPrzeliczenie, String jednostkaKoncowaPrzeliczenie){
        switch (jednostkaPoczatkowaPrzeliczenie){
            case "metr":
                wartosc = wartosc;
                break;
            case "paznokieć":
                wartosc = (float) (wartosc*0.0063);
                break;
            case "palec":
                wartosc = (float) (wartosc*0.038);
                break;
            case "stopa":
                wartosc = (float) (wartosc*0.2286);
                break;
        }

        switch (jednostkaKoncowaPrzeliczenie){
            case "metr":
                wartosc = wartosc;
                break;
            case "paznokieć":
                wartosc =  (float) (wartosc/0.0063);
            break;
            case "palec":
                wartosc =  (float) (wartosc/0.038);
            break;
            case "stopa":
                wartosc =  (float) (wartosc/0.2286);
            break;
        }

        return wartosc;
    }

    private void ustawSluchaczeDlaSpinnerow(){

    }
}