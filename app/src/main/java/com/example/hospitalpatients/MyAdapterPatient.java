package com.example.hospitalpatients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.example.hospitalpatients.Models.Patient;

public class MyAdapterPatient extends FirebaseRecyclerAdapter<Patient, MyAdapterPatient.myviewholder> {

    public MyAdapterPatient(@NonNull FirebaseRecyclerOptions<Patient> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Patient model) {
        holder.patName.setText(model.getPatient());
        holder.patAge.setText(model.getAge());
        holder.patCard.setText(model.getCardnumber());
        holder.patDiagnosis.setText(model.getDiagnosis());
        holder.patDoc.setText(model.getDoctor());
        holder.patHospitalization.setText(model.getHospitalization());
        holder.patLocation.setText(model.getLocation());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_patient,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        TextView patName, patAge, patCard, patDiagnosis, patDoc, patHospitalization, patLocation;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            patName = itemView.findViewById(R.id.patName);
            patAge = itemView.findViewById(R.id.patAge);
            patCard = itemView.findViewById(R.id.patCard);
            patDiagnosis = itemView.findViewById(R.id.patDiagnosis);
            patDoc = itemView.findViewById(R.id.patDoc);
            patHospitalization = itemView.findViewById(R.id.patHospitalization);
            patLocation = itemView.findViewById(R.id.patLocation);
        }
    }

}
