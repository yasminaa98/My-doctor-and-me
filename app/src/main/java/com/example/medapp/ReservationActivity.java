package com.example.medapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.medapp.Chat_Patient_Med.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
public class ReservationActivity extends AppCompatActivity{
    EditText horrair;
    Button reserver;
    private DatabaseReference mDatabaseReference,mPatient,mTime,mDoc;
    FirebaseAuth mAuth;
    private String userID;
    private FirebaseUser user;
    String[] specialities =  {"Dermatologist","Diagnostic radiology","Internal medicine","Medical genetics","Allergy and immunology"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        autoCompleteTxt=findViewById(R.id.auto_complete_txt);
        //spinner
        spinner=findViewById(R.id.spinner);
        items=new ArrayList<>();
        adapter= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,items);
        spinner.setAdapter(adapter);
        FirebaseDatabase.getInstance().getReference().child("Medecins").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //String nom=snapshot.child("Name").getValue().toString();
                    Users user = snapshot.getValue(Users.class);
                    String nom=user.getName();
                    items.add(nom);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String DocName =spinner.getSelectedItem().toString();
                Toast.makeText(ReservationActivity.this,"you selected "+DocName,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Log.d("docName",""+spinner.getSelectedItem().toString());
        //auto complete txt
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_doc,specialities);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT).show();

            }
        });
        //time
        horrair=findViewById(R.id.horraire);
        reserver=findViewById(R.id.reserver);
        horrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ReservationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        horrair.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });
        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user= FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                userID=user.getUid();
                mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Reservation").child(userID);
                Log.d("userid","userid"+mDatabaseReference);
                mAuth = FirebaseAuth.getInstance();
                mPatient= FirebaseDatabase.getInstance().getReference().child("Patients").child(userID);
                mPatient.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String DocName =spinner.getSelectedItem().toString();
                        String sp=autoCompleteTxt.getText().toString();
                        String nom = snapshot.child("Name").getValue().toString();
                        String Time=horrair.getText().toString();
                       /* mTime=FirebaseDatabase.getInstance().getReference().child("Reservation");
                        mTime.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String d = snapshot.child("DoctorsName").getValue().toString();
                                String t = snapshot.child("Time").getValue().toString();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    if ((d.equals(DocName)) && (t.equals(Time))) {
                                        Boolean verif = true;
                                    } else {
                                        Boolean verif = false;
                                    }
                                }
                                    if(verif=true){
                                        Toast.makeText(ReservationActivity.this,"this time is already used by another patient!",Toast.LENGTH_SHORT).show();
                                    }
                                    else if (d.equals(DocName)&& !t.equals(Time)){

                        */
                                        HashMap hashMap=new HashMap();
                                        hashMap.put("Patient",nom);
                                        hashMap.put("Time",Time);
                                        hashMap.put("DoctorsName",DocName);
                                        hashMap.put("speciality",sp);
                                        mDatabaseReference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(ReservationActivity.this,"Reservation Done !",Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }



                          /*   @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                           */

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }


        });

}
}