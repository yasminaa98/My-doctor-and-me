package com.example.medapp.Reservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.medapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Reservations_list extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database,NamedDB;
    MyAdapter myAdapter;
    ArrayList<Reservations> reservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations_list);
        recyclerView=findViewById(R.id.recycler_view_list);
        database= FirebaseDatabase.getInstance().getReference(  "Reservation");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reservations=new ArrayList<>();
        myAdapter=new MyAdapter(this,reservations);
        recyclerView.setAdapter(myAdapter);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        //Log.d("NameDB",""+NamedDB);

        NamedDB=FirebaseDatabase.getInstance().getReference().child("Medecins").child(userID);
        NamedDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name=snapshot.child("Name").getValue().toString();
                Log.d("Name",""+Name);
                database.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                          for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                              Reservations r = dataSnapshot.getValue(Reservations.class);
                              if (Name.equals(r.getDoctorsName())) {
                                        reservations.add(r);
                                    }
                                }
                                myAdapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Log.d("DocDB",""+DocDB);

    }
}