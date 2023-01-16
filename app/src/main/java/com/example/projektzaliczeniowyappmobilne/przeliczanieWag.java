package com.example.projektzaliczeniowyappmobilne;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class przeliczanieWag extends AppCompatActivity {
    Button goHome, przelicz;
    Spinner jednostkaBazowa, jednostkaKoncowa;
    EditText wartoscBazowa, wartoscKoncowa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_przeliczanie_wag);
        goHome = findViewById(R.id.home);
        przelicz = findViewById(R.id.przeliczWartosciWaga);
        wartoscBazowa = (EditText) findViewById(R.id.bazowaWartoscWaga);
        wartoscKoncowa = (EditText) findViewById(R.id.koncowaWartoscWaga);
        jednostkaBazowa =  findViewById(R.id.jednostkaBazowaWaga);
        jednostkaKoncowa =  findViewById(R.id.jednostkaKoncowaWaga);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.jednostkiWagi) );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.notifyDataSetChanged();
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

                jednostkaBazowa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            case "kilogram":
                wartosc = wartosc;
                break;
            case "funt":
                wartosc = (float) (wartosc*0.453592);
                break;
            case "uncja":
                wartosc = (float) (wartosc*0.02835);
                break;
            case "siodło":
                wartosc = (float) (wartosc*1.5);
                break;
        }

        switch (jednostkaKoncowaPrzeliczenie){
            case "kilogram":
                wartosc = wartosc;
                break;
            case "funt":
                wartosc =  (float) (wartosc/0.453592);
                break;
            case "uncja":
                wartosc =  (float) (wartosc/0.02835 );
                break;
            case "siodło":
                wartosc =  (float) (wartosc/1.5);
                break;
        }

        return wartosc;
    }
}