package com.example.hospitalpatients;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hospitalpatients.Models.Patient;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddPatient extends AppCompatActivity {
    String[] hospitalization = { "Экстренная", "Плановая"};
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://hospitalpatients-fbec2-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    private final ArrayList<String> arrayListDoc = new ArrayList<>();
    private final ArrayList<String> arrayListLoc = new ArrayList<>();
    private EditText textCardNumber, textPatient, textAgePatient, textDiagnosis;
    private Spinner spinnerHospitalization, spinnerDoctor, spinnerLocation;
    private DatabaseReference mDataBase;
    private ConstraintLayout add_patient_element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        spinnerHospitalization = findViewById(R.id.spinnerHospitalization);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hospitalization);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHospitalization.setAdapter(adapter);

        showDataSpinnerDoctor();
        showDataSpinnerLocation();

        add_patient_element = findViewById(R.id.add_patient_element);
        init();

        Button buttonAddPatient = findViewById(R.id.buttonAddPatient);
        buttonAddPatient.setOnClickListener(v -> {
            String cardnumber = textCardNumber.getText().toString();
            String patient = textPatient.getText().toString();
            String hospitalization = spinnerHospitalization.getSelectedItem().toString();
            String doctor = spinnerDoctor.getSelectedItem().toString();
            String location = spinnerLocation.getSelectedItem().toString();
            String age = textAgePatient.getText().toString();
            String diagnosis = textDiagnosis.getText().toString();
            Patient patients = new Patient(cardnumber, patient, hospitalization, doctor, location, age, diagnosis);

            if (TextUtils.isEmpty(textCardNumber.getText().toString())) {
                Snackbar.make(add_patient_element, "Введите номер медицинской карточки пациента", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(textPatient.getText().toString())) {
                Snackbar.make(add_patient_element, "Введите ФИО пациента", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(spinnerHospitalization.getSelectedItem().toString())) {
                Snackbar.make(add_patient_element, "Выберите тип госпитализации", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(spinnerDoctor.getSelectedItem().toString())) {
                Snackbar.make(add_patient_element, "Введите лечащего доктора", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(spinnerLocation.getSelectedItem().toString())) {
                Snackbar.make(add_patient_element, "Введите расположение пациента", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(textAgePatient.getText().toString())) {
                Snackbar.make(add_patient_element, "Введите возраст пациента", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(textDiagnosis.getText().toString())) {
                Snackbar.make(add_patient_element, "Введите диагноз пациента", Snackbar.LENGTH_SHORT).show();
            }
            else {
                mDataBase.push().setValue(patients);
                Snackbar.make(add_patient_element, "Пациент добавлен", Snackbar.LENGTH_SHORT).show();
                Intent intent=new Intent(AddPatient.this, UserMain.class);
                startActivity(intent);
            }
        });

        Button toIndex = findViewById(R.id.toIndex);
        toIndex.setOnClickListener(v -> startActivity(new Intent(AddPatient.this, UserMain.class)));
    }

    private void showDataSpinnerDoctor(){
        spinnerDoctor = findViewById(R.id.spinnerDoctor);
        databaseReference.child("Staff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayListDoc.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    arrayListDoc.add(item.child("doctor").getValue(String.class));
                }
                ArrayAdapter<String> arrayAdapterDoc = new ArrayAdapter<>(AddPatient.this, R.layout.style_spinner_doctor,arrayListDoc);
                spinnerDoctor.setAdapter(arrayAdapterDoc);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void showDataSpinnerLocation(){
        spinnerLocation = findViewById(R.id.spinnerLocation);
        databaseReference.child("Hospital").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayListLoc.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    arrayListLoc.add(item.child("locroom").getValue(String.class));
                }
                ArrayAdapter<String> arrayAdapterLoc = new ArrayAdapter<>(AddPatient.this, R.layout.style_spinner_location,arrayListLoc);
                spinnerLocation.setAdapter(arrayAdapterLoc);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void init(){
        textCardNumber = findViewById(R.id.textCardNumber);
        textPatient = findViewById(R.id.textPatient);
        spinnerHospitalization = findViewById(R.id.spinnerHospitalization);
        spinnerDoctor = findViewById(R.id.spinnerDoctor);
        spinnerLocation = findViewById(R.id.spinnerLocation);
        textAgePatient= findViewById(R.id.textAgePatient);
        textDiagnosis = findViewById(R.id.textDiagnosis);
        String db = "Patients";
        mDataBase = FirebaseDatabase.getInstance("https://hospitalpatients-fbec2-default-rtdb.europe-west1.firebasedatabase.app/").getReference(db);
    }
}