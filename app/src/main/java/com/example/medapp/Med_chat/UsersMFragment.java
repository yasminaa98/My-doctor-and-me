package com.example.medapp.Med_chat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medapp.Chat_Patient_Med.UserAdapter;
import com.example.medapp.Chat_Patient_Med.Users;
import com.example.medapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UsersMFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserMAdapter userMAdapter;
    private List<Meds> mUsers;

    public UsersMFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        @SuppressLint("ResourceType")
        View view=inflater.inflate(R.layout.fragment_users,container,false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mUsers =new ArrayList<>();
        readUsers();
        return view;
    }
    private void readUsers() {
       final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Patients");
         reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUsers.clear();
                for(DataSnapshot s:snapshot.getChildren()){
                    Meds users=s.getValue(Meds.class);
                    assert users!=null;
                    assert firebaseUser!=null;
                    if (!users.getuserid().equals(firebaseUser.getUid())) {
                        mUsers.add(users);
                    }
                }
                userMAdapter=new UserMAdapter(getContext(),mUsers, true);
                recyclerView.setAdapter(userMAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}