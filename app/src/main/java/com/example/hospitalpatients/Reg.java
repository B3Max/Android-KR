package com.example.hospitalpatients;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hospitalpatients.Models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Reg extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    ConstraintLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance("https://hospitalpatients-fbec2-default-rtdb.europe-west1.firebasedatabase.app/");
        users = db.getReference("Users");
        root = findViewById(R.id.root_element);

        Button toMain = findViewById(R.id.buttonLogOut);
        toMain.setOnClickListener(v -> {
            Intent intent = new Intent(Reg.this, MainActivity.class);
            startActivity(intent);
        });

        final EditText textLogin = findViewById(R.id.textLogin);
        final EditText textPassword = findViewById(R.id.textPassword);
        final EditText textRepeatPassword = findViewById(R.id.textRepeatPassword);

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(v -> {
            if (TextUtils.isEmpty(textLogin.getText().toString())) {
                Snackbar.make(root, "Введите почту", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(textPassword.getText().toString())) {
                Snackbar.make(root, "Введите пароль", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (textPassword.getText().toString().length() < 6) {
                Snackbar.make(root, "Пароль должен быть больше 6 символов", Snackbar.LENGTH_SHORT).show();
                return;
            }

            if (!(textRepeatPassword.getText().toString().equals(textPassword.getText().toString()))) {
                Snackbar.make(root, "Пароли не совпадают", Snackbar.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(textLogin.getText().toString(), textPassword.getText().toString())
                    .addOnSuccessListener(authResult -> {
                        User user = new User();
                        user.setLogin(textLogin.getText().toString());
                        user.setPass(textPassword.getText().toString());
                        Snackbar.make(root, "Вы зарегистрированы", Snackbar.LENGTH_SHORT).show();

                        users.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                .setValue(user).addOnSuccessListener(aVoid -> {
                                    Intent intent = new Intent(Reg.this, MainActivity.class);
                                    startActivity(intent);
                                });
                    }).addOnFailureListener(e -> Snackbar.make(root, "Пользователь существует. " + e.getMessage(), Snackbar.LENGTH_SHORT).show());

        });
    }
}