package com.example.medapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.medapp.Session_Patient.LoginActivity;
import com.example.medapp.Med_session.Login_Med_Activity;

public class WelcomeActivity extends AppCompatActivity {
    Button patient,medecin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        patient=findViewById(R.id.patient);
        medecin=findViewById(R.id.medecin);

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });

        medecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, Login_Med_Activity.class));
            }
        });
    }
}