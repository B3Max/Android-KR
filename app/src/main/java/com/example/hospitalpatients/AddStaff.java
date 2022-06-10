package com.example.hospitalpatients;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hospitalpatients.Models.Staff;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStaff extends AppCompatActivity {
    private EditText textId, textDoctor;
    private DatabaseReference mDataBase;
    private ConstraintLayout add_staff_element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        add_staff_element = findViewById(R.id.add_staff_element);
        init();

        Button addDoc = findViewById(R.id.addDoc);
        addDoc.setOnClickListener(v -> {
            String id = textId.getText().toString();
            String doctor = textDoctor.getText().toString();
            Staff staff = new Staff(id, doctor);

            if (TextUtils.isEmpty(textId.getText().toString())) {
                Snackbar.make(add_staff_element, "Введите ID сотрудника", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(textDoctor.getText().toString())) {
                Snackbar.make(add_staff_element, "Введите ФИО сотрудника", Snackbar.LENGTH_SHORT).show();
            }
            else {
                mDataBase.push().setValue(staff);
                Snackbar.make(findViewById(android.R.id.content), "Сотрудник добавлен", Snackbar.LENGTH_SHORT).show();
                Intent intent=new Intent(AddStaff.this, UserMain.class);
                startActivity(intent);

            }
        });
        Button toIndex = findViewById(R.id.toIndex);
        toIndex.setOnClickListener(v -> startActivity(new Intent(AddStaff.this, UserMain.class)));
    }
    private void init(){
        textId = findViewById(R.id.textId);
        textDoctor = findViewById(R.id.textDoctor);
        String db = "Staff";
        mDataBase = FirebaseDatabase.getInstance("https://hospitalpatients-fbec2-default-rtdb.europe-west1.firebasedatabase.app/").getReference(db);
    }
}