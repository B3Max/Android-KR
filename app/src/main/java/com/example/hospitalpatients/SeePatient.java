package com.example.hospitalpatients;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalpatients.Models.Patient;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SeePatient extends AppCompatActivity {

    RecyclerView recviewPat;
    MyAdapterPatient adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_patient);

        recviewPat = findViewById(R.id.recviewPat);
        recviewPat.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Patient> options =
                new FirebaseRecyclerOptions.Builder<Patient>()
                        .setQuery(FirebaseDatabase.getInstance("https://hospitalpatients-fbec2-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Patients"), Patient.class)
                        .build();

        adapter = new MyAdapterPatient(options);
        recviewPat.setAdapter(adapter);

        Button toIndex = findViewById(R.id.toIndex);
        toIndex.setOnClickListener(v -> {
            if (FirebaseAuth.getInstance().getCurrentUser() == null){
                startActivity(new Intent(SeePatient.this, Guest.class));
            }
            else {
                startActivity(new Intent(SeePatient.this, UserMain.class));
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}