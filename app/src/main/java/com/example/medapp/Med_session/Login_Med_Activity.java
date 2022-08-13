package com.example.medapp.Med_session;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Med_Activity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    EditText email, pwd;
    TextView register;
    Button login;
    private DatabaseReference mDocDB;
    private String userID;
    private FirebaseUser user;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_med);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        login = findViewById(R.id.login_btn);
        register=findViewById(R.id.register);
        mFirebaseAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login_Med_Activity.this, Register_Med_Activity.class));

            }
        });

    }
    private void loginUser (){
        String Email = email.getText().toString();
        String password = pwd.getText().toString();

        if (TextUtils.isEmpty(Email)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            pwd.setError("Password cannot be empty");
            pwd.requestFocus();
        }else{
            mFirebaseAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        user= FirebaseAuth.getInstance().getCurrentUser();
                        userID=user.getUid();
                        mDocDB= FirebaseDatabase.getInstance().getReference().child("Medecins");
                        mDocDB.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if (snapshot.hasChild(userID)) {
                                    Toast.makeText(Login_Med_Activity.this, "Doctor logged in successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login_Med_Activity.this, Main_Med_Activity.class));
                                }
                                else{
                                    Toast.makeText(Login_Med_Activity.this, "this user is not a doctor ", Toast.LENGTH_SHORT).show();


                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }else{
                        Toast.makeText(Login_Med_Activity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

