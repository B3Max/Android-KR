package com.example.hospitalpatients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.example.hospitalpatients.Models.Staff;

public class MyAdapterDoctor extends FirebaseRecyclerAdapter<Staff, MyAdapterDoctor.myviewholder> {

    public MyAdapterDoctor(@NonNull FirebaseRecyclerOptions<Staff> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Staff model) {
        holder.doctorText.setText(model.getDoctor());
        holder.idText.setText(model.getId());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_doctor,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        TextView doctorText, idText;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            doctorText = itemView.findViewById(R.id.doctorText);
            idText = itemView.findViewById(R.id.idText);
        }
    }

}
