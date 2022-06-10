package com.example.hospitalpatients;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button buttonGuest, buttonReg, buttonEnter;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    ConstraintLayout main_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGuest = findViewById(R.id.buttonGuest);
        buttonReg = findViewById(R.id.buttonReg);
        buttonEnter = findViewById(R.id.buttonEnter);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance("https://hospitalpatients-fbec2-default-rtdb.europe-west1.firebasedatabase.app/");
        users = db.getReference("Users");
        main_enter = findViewById(R.id.main_enter);

        buttonReg.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Reg.class);
            startActivity(intent);
        });

        buttonGuest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Guest.class);
            startActivity(intent);
        });

        final EditText enterLogin = findViewById(R.id.enterLogin);
        final EditText enterPassword = findViewById(R.id.enterPassword);

        buttonEnter.setOnClickListener(v -> {
            if (TextUtils.isEmpty(enterLogin.getText().toString())) {
                Snackbar.make(main_enter, "Введите почту", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(enterPassword.getText().toString())) {
                Snackbar.make(main_enter, "Введите пароль", Snackbar.LENGTH_SHORT).show();
            }
            else {
                auth.signInWithEmailAndPassword(enterLogin.getText().toString(), enterPassword.getText().toString())
                        .addOnSuccessListener(authResult -> {
                            startActivity(new Intent(MainActivity.this, UserMain.class));
                            finish();
                        }).addOnFailureListener(e -> Snackbar.make(main_enter, "Ошибка авторизации. " + e.getMessage(), Snackbar.LENGTH_SHORT).show());
            }
        });
    }

}
