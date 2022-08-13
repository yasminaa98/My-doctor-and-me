package com.example.medapp.Med_session;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


public class Register_Med_Activity extends AppCompatActivity {
    EditText Temail,Tname,Tspecialite,Tphone,pwd,Taddress,Tregion;
    Button btnRegister;
    FirebaseAuth dAuth;
    private DatabaseReference mDocDB;
    private String userID;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_med);
        Taddress=findViewById(R.id._address);
        Tregion=findViewById(R.id._region);
        pwd=findViewById(R.id.pwd);
        Temail = findViewById(R.id.email);
        Tspecialite = findViewById(R.id.specialite);
        btnRegister = findViewById(R.id.register_btn);
        Tphone=findViewById(R.id.phone);
        Tname=findViewById(R.id.name);
        dAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(view ->{
            createDoc();
        });

    }

    private void createDoc() {
        String email = Temail.getText().toString();
        String password = pwd.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Temail.setError("Email cannot be empty");
            Temail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            pwd.setError("Password cannot be empty");
            pwd.requestFocus();
        } else {
            dAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register_Med_Activity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        dAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    user= FirebaseAuth.getInstance().getCurrentUser();
                                    assert user != null;
                                    userID=user.getUid();
                                    mDocDB= FirebaseDatabase.getInstance().getReference().child("Medecins").child(userID);
                                    dAuth = FirebaseAuth.getInstance();
                                    String Name=Tname.getText().toString();
                                    String email=Temail.getText().toString();
                                    String Phone=Tphone.getText().toString();
                                    String Speciality=Tspecialite.getText().toString();
                                    String Address=Taddress.getText().toString();
                                    String Region=Tregion.getText().toString();
                                    int phone=Integer.parseInt(Phone);
                                    HashMap<String, Object> hashMap=new HashMap<>();
                                    hashMap.put("userid",userID);
                                    hashMap.put("Name",Name);
                                    hashMap.put("Email",email);
                                    hashMap.put("speciality",Speciality);
                                    hashMap.put("Phone",phone);
                                    hashMap.put("Address",Address);
                                    hashMap.put("Region",Region);
                                    mDocDB.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Register_Med_Activity.this,"Data updated !",Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    startActivity(new Intent(Register_Med_Activity.this, Main_Med_Activity.class));
                                }else{
                                    Toast.makeText(Register_Med_Activity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {
                        Toast.makeText(Register_Med_Activity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }


    }

}
