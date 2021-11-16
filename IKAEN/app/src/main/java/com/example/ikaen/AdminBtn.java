package com.example.ikaen;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminBtn extends AppCompatActivity {


    private int stop, start;
    private Button sendDatabtn, sendstopData;
    private TextView mMessageEditText;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_btn);

        start = 1 ;
        stop = 0;

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("device");

        mMessageEditText = findViewById(R.id.textView3);
        sendDatabtn = findViewById(R.id.startBtn);
        sendstopData = findViewById(R.id.stopBtn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel("Notification","Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }



        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference storageReference = firebaseDatabase.getReference("Device");
                DatabaseReference ActivityHistory = firebaseDatabase.getReference("ActivityHis");

                FeedbackDetails feedbackDetails = new FeedbackDetails(1);
                storageReference.setValue(feedbackDetails);

                FeedbackDetails feedbackDetails1 = new FeedbackDetails(1);
                ActivityHistory.setValue(feedbackDetails1);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminBtn.this, "Notification");
                builder.setContentTitle("Device Notification");
                builder.setContentText("The device is being used right now");
                builder.setSmallIcon(R.drawable.stopwatch);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminBtn.this);
                managerCompat.notify(1, builder.build());

                Toast.makeText(AdminBtn.this, "device activating", Toast.LENGTH_SHORT).show();




            }

        });

        sendstopData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference storageReference = firebaseDatabase.getReference("Device");
                DatabaseReference ActivityHistory = firebaseDatabase.getReference("ActivityHis");

                FeedbackDetails feedbackDetails = new FeedbackDetails(0);
                storageReference.setValue(feedbackDetails);

                FeedbackDetails feedbackDetails1 = new FeedbackDetails(0);
                ActivityHistory.setValue(feedbackDetails1);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(AdminBtn.this, "Notification");
                builder.setContentTitle("Device Notification");
                builder.setContentText("The device have stop from used");
                builder.setSmallIcon(R.drawable.stopwatch);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(AdminBtn.this);
                managerCompat.notify(1, builder.build());


                Toast.makeText(AdminBtn.this, "pulling device", Toast.LENGTH_SHORT).show();
            }
        });

    }


}