package com.example.examplefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        Employee emp = new Employee("Chris", "Scharff");


        DatabaseReference myRef = database.getReference("employees");
        myRef.push().setValue(emp);


        myRef.push().setValue(emp);
        myRef.child("6").setValue(new Employee("Alex", "Jones"));

        // Read from the database

        myRef = database.getReference().child("employees");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Employee emp;
                int counter = 0;
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    emp = (Employee)ds.getValue(Employee.class);
                    Log.i("MAINACTIVITY", counter + " - Firstname: " + emp.getFirstName()
                            + " Lastname: " + emp.getLastName());
                    counter = counter+1;
                }
                Log.i("MAINACTIVITY", "END");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}