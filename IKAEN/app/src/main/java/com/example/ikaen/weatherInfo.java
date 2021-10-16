
package com.example.ikaen;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class weatherInfo extends AppCompatActivity {

    TextView celciusPer, humidtyPer, pressurePer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseDatabase databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);

        humidtyPer = findViewById(R.id.humidity_hpa);
        celciusPer = findViewById(R.id.tempature_c);
        pressurePer = findViewById(R.id.Pressure_level);
        databaseReference = FirebaseDatabase.getInstance();


        DatabaseReference temp = database.getReference("Tempature/");

        temp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Map<String, Object> temp = (Map<String, Object>) snapshot.getValue();
                Log.d("owo","dapat" + temp.get("celcius"));
                celciusPer.setText(temp.get("celcius").toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference humi = database.getReference("Humidity/");

        humi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Map<String, Object> humi = (Map<String, Object>) snapshot.getValue();
                Log.d("uwu","dapat" + humi.get("humidity"));
                humidtyPer.setText(humi.get("humidity").toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference pres = database.getReference("Pressure/");

        pres.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Map<String, Object> pres = (Map<String, Object>) snapshot.getValue();
                Log.d("owo","dapat" + pres.get("Hpa"));
                pressurePer.setText(pres.get("Hpa").toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




















    }
}