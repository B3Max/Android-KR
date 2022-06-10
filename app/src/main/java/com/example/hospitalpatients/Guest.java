package com.example.hospitalpatients;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Guest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        Button buttonLogOut = findViewById(R.id.buttonLogOut);
        buttonLogOut.setOnClickListener(v -> {
            Intent intent = new Intent(Guest.this, MainActivity.class);
            startActivity(intent);
        });

        Button seeDoc = findViewById(R.id.seeDoc);
        seeDoc.setOnClickListener(v -> {
            Intent intent = new Intent(Guest.this, SeeDoc.class);
            startActivity(intent);
        });

        Button seeHos = findViewById(R.id.seeHos);
        seeHos.setOnClickListener(v -> {
            Intent intent = new Intent(Guest.this, SeeHos.class);
            startActivity(intent);
        });
    }

}