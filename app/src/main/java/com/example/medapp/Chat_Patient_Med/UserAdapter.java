package com.example.medapp.Chat_Patient_Med;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medapp.R;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private Context mContext;
    private List<Users> mUsers;
    private boolean ischat;


    public UserAdapter(Context mContext, List<Users> mUsers, boolean ischat) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.ischat = ischat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.users_item,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
     public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users=mUsers.get(position);
        holder.name.setText(users.getName());
        holder.sp.setText(users.getspeciality());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,P_MessagesActivity.class);
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
        public TextView name,sp;
        public ViewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.username);
            sp=itemView.findViewById(R.id.speciality);
        }
    }
}
