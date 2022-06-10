package com.example.hospitalpatients;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hospitalpatients.Models.Hospital;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHos extends AppCompatActivity {
    private EditText textLocroom, textCorpnum, textDepartment, textRoom;
    private DatabaseReference mDataBase;
    private ConstraintLayout add_hos_element;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hos);
        add_hos_element = findViewById(R.id.add_staff_element);
        init();

        Button addPlace = findViewById(R.id.addPlace);
        addPlace.setOnClickListener(v -> {
            String locroom = textLocroom.getText().toString();
            String corpnum = textCorpnum.getText().toString();
            String department = textDepartment.getText().toString();
            String room = textRoom.getText().toString();
            Hospital hospital = new Hospital(locroom, corpnum, department, room);

            if (TextUtils.isEmpty(textLocroom.getText().toString())) {
                Snackbar.make(add_hos_element, "Введите ID сотрудника", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(textCorpnum.getText().toString())) {
                Snackbar.make(add_hos_element, "Введите номер корпуса", Snackbar.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(textDepartment.getText().toString())) {
                Snackbar.make(add_hos_element, "Введите название отделения", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(textRoom.getText().toString())) {
                Snackbar.make(add_hos_element, "Введите номер палаты", Snackbar.LENGTH_SHORT).show();
            }
            else {
                mDataBase.push().setValue(hospital);
                Snackbar.make(findViewById(android.R.id.content), "Местоположение добавлено", Snackbar.LENGTH_SHORT).show();
                Intent intent=new Intent(AddHos.this, UserMain.class);
                startActivity(intent);
            }
        });
        Button toIndex = findViewById(R.id.toIndex);
        toIndex.setOnClickListener(v -> startActivity(new Intent(AddHos.this, UserMain.class)));
    }
    private void init(){
        textLocroom = findViewById(R.id.textLocroom);
        textCorpnum = findViewById(R.id.textCorpnum);
        textDepartment= findViewById(R.id.textDepartment);
        textRoom = findViewById(R.id.textRoom);
        String db = "Hospital";
        mDataBase = FirebaseDatabase.getInstance("https://hospitalpatients-fbec2-default-rtdb.europe-west1.firebasedatabase.app/").getReference(db);
    }
}