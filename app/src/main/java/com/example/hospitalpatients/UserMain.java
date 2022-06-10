package com.example.hospitalpatients;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class UserMain extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        String db = "Users";
//        DatabaseReference mDataBase = FirebaseDatabase.getInstance("https://hospitalpatients-fbec2-default-rtdb.europe-west1.firebasedatabase.app/").getReference(db);
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        DatabaseReference rootRef = FirebaseDatabase.getInstance("https://hospitalpatients-fbec2-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        DatabaseReference userRef = rootRef.child(db);
        DatabaseReference current_userRef = userRef.child(uid);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String login = dataSnapshot.child("login").getValue(String.class);
                assert login != null;
                if (login.equals("admin@admin.com")) {
                    setContentView(R.layout.activity_admin_main);

                    Button seeDoc = findViewById(R.id.seeDoc);
                    seeDoc.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, SeeDoc.class);
                        startActivity(intent);
                    });

                    Button seeHos = findViewById(R.id.seeHos);
                    seeHos.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, SeeHos.class);
                        startActivity(intent);
                    });

                    Button buttonEmergency = findViewById(R.id.seePatient);
                    buttonEmergency.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, SeePatient.class);
                        startActivity(intent);
                    });

                    Button addDoctor = findViewById(R.id.addDoctor);
                    addDoctor.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, AddStaff.class);
                        startActivity(intent);
                    });

                    Button addHospital = findViewById(R.id.addHospital);
                    addHospital.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, AddHos.class);
                        startActivity(intent);
                    });

                    Button addPatient = findViewById(R.id.addPatient);
                    addPatient.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, AddPatient.class);
                        startActivity(intent);
                    });

                    Button delPatient = findViewById(R.id.delPatient);
                    delPatient.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, DelPatient.class);
                        startActivity(intent);
                    });

                    Button buttonLogOut = findViewById(R.id.buttonLogOut);
                    buttonLogOut.setOnClickListener(v -> {
                        auth.signOut();
                        startActivity(new Intent(UserMain.this, MainActivity.class));
                    });
                } else if (login.equals("doctor@doctor.com")) {
                    setContentView((R.layout.activity_doctor_main));

                    Button seeDoc = findViewById(R.id.seeDoc);
                    seeDoc.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, SeeDoc.class);
                        startActivity(intent);
                    });

                    Button seeHos = findViewById(R.id.seeHos);
                    seeHos.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, SeeHos.class);
                        startActivity(intent);
                    });

                    Button buttonEmergency = findViewById(R.id.seePatient);
                    buttonEmergency.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, SeePatient.class);
                        startActivity(intent);
                    });

                    Button addPatient = findViewById(R.id.addPatient);
                    addPatient.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, AddPatient.class);
                        startActivity(intent);
                    });

                    Button buttonLogOut = findViewById(R.id.buttonLogOut);
                    buttonLogOut.setOnClickListener(v -> {
                        auth.signOut();
                        startActivity(new Intent(UserMain.this, MainActivity.class));
                    });
                } else {
                    setContentView(R.layout.activity_user_main);
                    Button seeDoc = findViewById(R.id.seeDoc);
                    seeDoc.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, SeeDoc.class);
                        startActivity(intent);
                    });

                    Button seeHos = findViewById(R.id.seeHos);
                    seeHos.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, SeeHos.class);
                        startActivity(intent);
                    });

                    Button buttonEmergency = findViewById(R.id.seePatient);
                    buttonEmergency.setOnClickListener(v -> {
                        Intent intent = new Intent(UserMain.this, SeePatient.class);
                        startActivity(intent);
                    });

                    Button buttonLogOut = findViewById(R.id.buttonLogOut);
                    buttonLogOut.setOnClickListener(v -> {
                        auth.signOut();
                        startActivity(new Intent(UserMain.this, MainActivity.class));
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        current_userRef.addListenerForSingleValueEvent(eventListener);

    }
}