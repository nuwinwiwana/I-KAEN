package com.example.ikaen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import static android.content.ContentValues.TAG;


public class Admin extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ikaen-a3973-default-rtdb.asia-southeast1.firebasedatabase.app/");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String UID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    TextView  fullname;
    DatabaseReference myRef = database.getReference("users/" + UID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        fullname = findViewById(R.id.textTitle);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView widget = findViewById(R.id.widget);
        final CardView c1 = findViewById(R.id.card1);
        final CardView c2 = findViewById(R.id.card2);
        final CardView c3 = findViewById(R.id.card3);
        final CardView c4 = findViewById(R.id.card4);
        final TextView more = findViewById(R.id.more);



        widget.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, setting.class));
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, timer.class));
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button coding that connect dgn firebase
                //tak siap lagi but esok saya sambung
                Toast.makeText(Admin.this, "Gears are Moving", Toast.LENGTH_SHORT).show();

                myRef.setValue("Hello, World!");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d(TAG, "Value is:  " + value);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "helloWorld", error.toException());
                    }
                });
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, activityHistory.class));
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, camera.class));
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, weatherInfo.class));
            }
        });

    }

}
