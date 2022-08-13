package com.example.medapp.Reservation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medapp.R;
import com.example.medapp.Session_Patient.PatientProfileActivity;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Reservations> reservations;

    public MyAdapter(Context context, ArrayList<Reservations> reservations) {
        this.context = context;
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.reservation_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Reservations r=reservations.get(position);
        holder.Time.setText(r.getTime());
        holder.Patient.setText(r.getPatient());
        holder.DocName.setText(r.getDoctorsName());

    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Patient,DocName,Time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Patient= itemView.findViewById(R.id.Patient_name);
            DocName=itemView.findViewById(R.id.DocName);
            Time=itemView.findViewById(R.id.Time);
        }
    }
}
