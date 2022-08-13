package com.example.medapp.Med_session;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Med_profile extends AppCompatActivity {
    EditText name_,phone_,sp_,email_,address_,region_ ;
    Button update;
    private DatabaseReference mDocDB;
    String _NAME,_PHONE,_SP,_EMAIL,_ADDRESS,_REGION;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_profile);
        address_=findViewById(R.id.address);
        region_=findViewById(R.id.region);
        name_=findViewById(R.id._name);
        phone_=findViewById(R.id._phone);
        email_=findViewById(R.id._email);
        sp_=findViewById(R.id._speciality);
        update=findViewById(R.id._update);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        mDocDB=FirebaseDatabase.getInstance().getReference().child("Medecins").child(userID);
        //mDocDB.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
         mDocDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String _name_ = snapshot.child("Name").getValue().toString();
                String _sp_ = snapshot.child("speciality").getValue().toString();
                String _phone_ = snapshot.child("Phone").getValue().toString();
                String _email_ = snapshot.child("Email").getValue().toString();
                String _address_ = snapshot.child("Address").getValue().toString();
                String _region_ = snapshot.child("Region").getValue().toString();
                name_.setText(_name_);
                sp_.setText(_sp_);
                phone_.setText(_phone_);
                email_.setText(_email_);
                address_.setText(_address_);
                region_.setText(_region_);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDoc();
            }
        });

    }

    private void updateDoc(){
        _NAME=name_.getText().toString();
        _PHONE=phone_.getText().toString();
        _SP=sp_.getText().toString();
        _EMAIL=email_.getText().toString();
        _ADDRESS=address_.getText().toString();
        _REGION=region_.getText().toString();
        int Phone=Integer.parseInt(_PHONE);
        HashMap<String, Object> hashMap= new HashMap<String, Object>();
        hashMap.put("Name",_NAME);
        hashMap.put("speciality",_SP);
        hashMap.put("Phone",Phone);
        hashMap.put("Email",_EMAIL);
        hashMap.put("Address",_ADDRESS);
        hashMap.put("Region",_REGION);
        mDocDB.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Med_profile.this,"Data updated !",Toast.LENGTH_SHORT).show();
            }
        }); 
    }

}