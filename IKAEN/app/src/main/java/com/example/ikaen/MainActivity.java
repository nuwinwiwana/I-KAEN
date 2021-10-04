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
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Objects;

import static android.content.ContentValues.TAG;


public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ikaen-a3973-default-rtdb.asia-southeast1.firebasedatabase.app/");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String UID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    TextView  fullname;
    DatabaseReference myRef = database.getReference("message");


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
                startActivity(new Intent(MainActivity.this, setting.class));
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, timer.class));
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, spam.class));



            }
        });



        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activityHistory.class));
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, camera.class));
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, weatherInfo.class));
            }
        });

    }



}
