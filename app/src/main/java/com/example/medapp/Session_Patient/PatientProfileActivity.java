package com.example.medapp.Session_Patient;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class PatientProfileActivity extends AppCompatActivity {
    EditText name,phone,age,email ;
    Button update;
    private DatabaseReference mPatientDB;
    String NAME,PHONE,AGE,EMAIL;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        age=findViewById(R.id.age);
        update=findViewById(R.id.update);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        mPatientDB=FirebaseDatabase.getInstance().getReference().child("Patients").child(userID);
        //mPatientDB.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            mPatientDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String _name = snapshot.child("Name").getValue().toString();
                    String _age = snapshot.child("Age").getValue().toString();
                    String _phone = snapshot.child("Phone").getValue().toString();
                    String _email = snapshot.child("Email").getValue().toString();
                    name.setText(_name);
                    age.setText(_age);
                    phone.setText(_phone);
                    email.setText(_email);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePatient();
            }
        });

    }

     private void updatePatient(){
        NAME=name.getText().toString();
        PHONE=phone.getText().toString();
        AGE=age.getText().toString();
        EMAIL=email.getText().toString();
        int Age =Integer.parseInt(AGE);
        int Phone=Integer.parseInt(PHONE);
        HashMap<String, Object> hashMap=new HashMap<String, Object>();
        hashMap.put("Name",NAME);
        hashMap.put("Age",Age);
        hashMap.put("Phone",Phone);
        hashMap.put("Email",EMAIL);
        mPatientDB.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(PatientProfileActivity.this,"Data updated !",Toast.LENGTH_SHORT).show();
            }
        });
    }

}