package com.example.ikaen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static android.content.ContentValues.TAG;


public class ProfilePage extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String UID;

    TextView fullname;
    TextView email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        fullname=findViewById(R.id.fullnametv);
        email=findViewById(R.id.emailtv);
        getprofile();


    }
    public void getprofile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            UID = user.getUid();
            // User is signed in
        } else {
            // No user is signed in
        }

        DatabaseReference myRef = database.getReference("users/"+UID);



        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                usermodel user = dataSnapshot.getValue(usermodel.class);
                fullname.setText(user.getFullname());
                email.setText(user.getemail());
                Log.d(TAG, "Value is: " + user.getFullname());
                Log.d(TAG, "Value is: " + user.getemail());
                Log.d(TAG, "Value is: " + user.getPhone());
                Log.d(TAG, "Value is: " + user.getPassword());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
            }
        }