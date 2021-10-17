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

    TextView statusTemp,statusPressure,statusHumi;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseDatabase databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        statusTemp = findViewById(R.id.statusTemp);
        statusPressure = findViewById(R.id.statusPressure);
        statusHumi = findViewById(R.id.statusHumi);
        databaseReference = FirebaseDatabase.getInstance();


        DatabaseReference temp = database.getReference("Tempature/");

        temp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Map<String, Object> temp = (Map<String, Object>) snapshot.getValue();
                Log.d("owo","dapat" + temp.get("celcius"));

                statusTemp.setText("The Tempature is " + temp.get("celcius").toString());

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
                statusHumi.setText("The Humidity Level is " + humi.get("humidity").toString());
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
                statusPressure.setText("The Pressure Level is " + pres.get("Hpa").toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}