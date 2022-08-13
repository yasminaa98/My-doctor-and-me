package com.example.medapp.Doctors_Profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.medapp.R;
import java.util.ArrayList;

public class Profile_Adapter extends RecyclerView.Adapter<Profile_Adapter.MyViewHolder> {
        Context context;
        ArrayList<Doctors> docs;



        public Profile_Adapter(Context context, ArrayList<Doctors> docs) {
            this.context = context;
            this.docs = docs;
        }

        @NonNull
        @Override
        public Profile_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(context).inflate(R.layout.docs_item,parent,false);
            return new Profile_Adapter.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull Profile_Adapter.MyViewHolder holder, int position) {
            Doctors d=docs.get(position);
            holder.Name.setText(d.getName());
            String phone=String.valueOf(d.getPhone());
            holder.phone.setText(phone);
            holder.speciality.setText(d.getspeciality());
            holder.Region.setText(d.getRegion());
            holder.Address.setText(d.getAddress());
        }
        @Override
        public int getItemCount() {
            return docs.size();
        }

    public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView Name,speciality,phone,Address,Region;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                Address=itemView.findViewById(R.id.add);
                Region=itemView.findViewById(R.id.reg);
                Name= itemView.findViewById(R.id.d_name);
                speciality=itemView.findViewById(R.id.sp);
                phone=itemView.findViewById(R.id.ph);
            }
        }
    public void filterList(ArrayList<Doctors> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        docs = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

}
