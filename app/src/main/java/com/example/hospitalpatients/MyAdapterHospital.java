package com.example.hospitalpatients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.example.hospitalpatients.Models.Hospital;

public class MyAdapterHospital extends FirebaseRecyclerAdapter<Hospital, MyAdapterHospital.myviewholder> {

    public MyAdapterHospital(@NonNull FirebaseRecyclerOptions<Hospital> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Hospital model) {
        holder.hosLocroom.setText(model.getLocroom());
        holder.hosCorp.setText(model.getCorpnum());
        holder.hosDepartment.setText(model.getDepartment());
        holder.hosRoom.setText(model.getRoom());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_hospital,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        TextView hosLocroom, hosCorp, hosDepartment, hosRoom;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            hosLocroom = itemView.findViewById(R.id.hosLocroom);
            hosCorp = itemView.findViewById(R.id.hosCorp);
            hosDepartment = itemView.findViewById(R.id.hosDepartment);
            hosRoom = itemView.findViewById(R.id.hosRoom);
        }
    }

}
