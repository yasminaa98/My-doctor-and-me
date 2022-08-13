package com.example.medapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.medapp.Chat_Patient_Med.ChatActivity;
import com.example.medapp.Doctors_Profile.Docs_ProfileActivity;
import com.example.medapp.Reservation.Reservation_list_P;
import com.example.medapp.Session_Patient.PatientProfileActivity;
import com.example.medapp.retrofit.RecyclerviewActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ImageView signout,profil,liste_med,chat,Reservations,appointement;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signout = findViewById(R.id.signout);
        chat=findViewById(R.id.chat);
        profil=findViewById(R.id.profil);
        liste_med=findViewById(R.id.list_medecin);
        appointement=findViewById(R.id.appointement);
        appointement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ReservationActivity.class);
                startActivity(i);

            }
        });

       Reservations=findViewById(R.id.reservation);
        Reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Reservation_list_P.class);
                startActivity(i);

            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PatientProfileActivity.class);
                startActivity(i);
            }
        });
        liste_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Docs_ProfileActivity.class);
                startActivity(i);

            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(i);

            }
        });
        mAuth = FirebaseAuth.getInstance();


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, com.example.medapp.Session_Patient.LoginActivity.class));

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, com.example.medapp.Session_Patient.LoginActivity.class));
        }
    }
    }
