package com.example.medapp.Med_session;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.medapp.Doctors_Profile.Docs_ProfileActivity;
import com.example.medapp.Med_chat.ChatMActivity;
import com.example.medapp.R;
import com.example.medapp.Reservation.Reservations_list;
import com.example.medapp.retrofit.RecyclerviewActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main_Med_Activity extends AppCompatActivity {
    ImageView signout,profilM,chat,Reservations;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_med);
        signout = findViewById(R.id.signout);
        profilM=findViewById(R.id.profilMed);
        Reservations=findViewById(R.id.reservation);
        Reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main_Med_Activity.this, Reservations_list.class);
                startActivity(i);

            }
        });
        chat=findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main_Med_Activity.this, ChatMActivity.class);
                startActivity(i);

            }
        });

        profilM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main_Med_Activity.this, Med_profile.class);
                startActivity(i);
            }
        });
        mAuth = FirebaseAuth.getInstance();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(Main_Med_Activity.this, Login_Med_Activity.class));

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(Main_Med_Activity.this, Login_Med_Activity.class));
        }
    }
}