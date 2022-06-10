package com.example.hospitalpatients;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalpatients.Models.Hospital;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SeeHos extends AppCompatActivity {
    RecyclerView recviewHos;
    MyAdapterHospital adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_hos);

        recviewHos = findViewById(R.id.recviewHos);
        recviewHos.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Hospital> options =
                new FirebaseRecyclerOptions.Builder<Hospital>()
                        .setQuery(FirebaseDatabase.getInstance("https://hospitalpatients-fbec2-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Hospital"), Hospital.class)
                        .build();

        adapter = new MyAdapterHospital(options);
        recviewHos.setAdapter(adapter);

        Button toIndex = findViewById(R.id.toIndex);
        toIndex.setOnClickListener(v -> {
            if (FirebaseAuth.getInstance().getCurrentUser() == null){
                startActivity(new Intent(SeeHos.this, Guest.class));
            }
            else {
                startActivity(new Intent(SeeHos.this, UserMain.class));
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