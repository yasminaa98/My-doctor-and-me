package com.example.medapp.Session_Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medapp.MainActivity;

import com.example.medapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {
    EditText Email,name,Age,phone,pwd;
    Button btnRegister;
    TextView LoginHere;
    FirebaseAuth mAuth;
    private DatabaseReference mPatientDB;
    private String userID;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pwd=findViewById(R.id.pwd);
        Email = findViewById(R.id.email);
        Age = findViewById(R.id.Age);
        btnRegister = findViewById(R.id.register_btn);
        phone=findViewById(R.id.phone);
        name=findViewById(R.id.name);
        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(view ->{
            createUser();
        });

    }
    private void createUser() {
        String email = Email.getText().toString();
        String password = pwd.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Email.setError("Email cannot be empty");
            Email.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            pwd.setError("Password cannot be empty");
            pwd.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    user= FirebaseAuth.getInstance().getCurrentUser();
                                    assert user != null;
                                    userID=user.getUid();
                                    mPatientDB= FirebaseDatabase.getInstance().getReference().child("Patients").child(userID);
                                    mAuth = FirebaseAuth.getInstance();
                                    String Name=name.getText().toString();
                                    String email=Email.getText().toString();
                                    String Phone=phone.getText().toString();
                                    String AGE=Age.getText().toString();
                                    int Age =Integer.parseInt(AGE);
                                    int phone=Integer.parseInt(Phone);
                                    HashMap hashMap=new HashMap();
                                    hashMap.put("userid",userID);
                                    hashMap.put("Name",Name);
                                    hashMap.put("Email",email);
                                    hashMap.put("Age",Age);
                                    hashMap.put("Phone",phone);
                                    mPatientDB.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(RegisterActivity.this,"Data updated !",Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                }else{
                                    Toast.makeText(RegisterActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }


    }

}
