package com.example.projektzaliczeniowyappmobilne;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button wagaCall,odlegloscCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wagaCall = findViewById(R.id.wagaCall);
        odlegloscCall = findViewById(R.id.odlegloscCall);

        wagaCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               otworzPodWidok(przeliczanieWag.class);
            }
        });

        odlegloscCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otworzPodWidok(przeliczanieOdleglosci.class);
            }
        });
    }

    protected void otworzPodWidok(final Class ActivityToOpen){
        Intent view = new Intent(this, ActivityToOpen);
        startActivity(view);
    }
}