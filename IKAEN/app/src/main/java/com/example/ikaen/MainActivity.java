
package com.example.ikaen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.HttpCookie;
import java.util.Objects;

import okhttp3.internal.cache.DiskLruCache;

import static android.content.ContentValues.TAG;


public class MainActivity extends AppCompatActivity {


    TextView  fullname,more, celciusPer, humidtyPer, pressurePer;
    CardView c1, c2,c3,c4;
    ImageView widget;
   // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ikaen-a3973-default-rtdb.asia-southeast1.firebasedatabase.app/");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseDatabase databaseReference;
    FirebaseDatabase Dbase = FirebaseDatabase.getInstance();
    String UID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    // FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullname = findViewById(R.id.textTitle);
        widget = findViewById(R.id.widget);
        c1 = findViewById(R.id.card1);
        c2 = findViewById(R.id.card2);
        c3 = findViewById(R.id.card3);
        c4 = findViewById(R.id.card4);
        more = findViewById(R.id.more);
        humidtyPer = findViewById(R.id.humidity_percent);
        celciusPer = findViewById(R.id.tempature_c);
        pressurePer = findViewById(R.id.pressure_percent);
       databaseReference = FirebaseDatabase.getInstance();




       DatabaseReference myref = Dbase.getReference("users/" + UID);

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                usermodel user = snapshot.getValue(usermodel.class);
                assert user != null;
                String name = user.getFullname();
                fullname.setText(name);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






         DatabaseReference temp = database.getReference().child("Tempature");

        temp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                usermodel sens = snapshot.getValue(usermodel.class);
                assert sens != null;
                String tempature = sens.getTempature();

                celciusPer.setText(tempature);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





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
                //startActivity(new Intent(MainActivity.this, activityHistory.class));
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
