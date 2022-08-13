package com.example.medapp.Med_chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medapp.Chat_Patient_Med.Users;
import com.example.medapp.R;

import java.util.List;

public class UserMAdapter extends RecyclerView.Adapter<UserMAdapter.ViewHolder>{
    private Context mContext;
    private List<Meds> mUsers;
    private boolean ischat;


    public UserMAdapter(Context mContext, List<Meds> mUsers, boolean ischat) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.ischat = ischat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.med_items,parent,false);
        return new UserMAdapter.ViewHolder(view);
    }

    @Override
     public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meds users=mUsers.get(position);
        holder.name.setText(users.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,M_MessagesActivity.class);
                intent.putExtra("userid",users.getuserid());
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ViewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.username);
        }
    }
}
