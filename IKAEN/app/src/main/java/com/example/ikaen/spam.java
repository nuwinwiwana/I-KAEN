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

import java.util.Objects;

public class spam extends AppCompatActivity {

    private int stop, start;
    private Button sendDatabtn, sendstopData;
    private TextView mMessageEditText;


    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    String UID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spam);

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

                FeedbackDetails feedbackDetails = new FeedbackDetails(1);
                storageReference.setValue(feedbackDetails);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(spam.this, "Notification");
                builder.setContentTitle("Device Notification");
                builder.setContentText("The device is being used right now");
                builder.setSmallIcon(R.drawable.stopwatch);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(spam.this);
                managerCompat.notify(1, builder.build());

                Toast.makeText(spam.this, "device activating", Toast.LENGTH_SHORT).show();


            }

        });

        sendstopData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference storageReference = firebaseDatabase.getReference("Device");

                FeedbackDetails feedbackDetails = new FeedbackDetails(0);
                storageReference.setValue(feedbackDetails);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(spam.this, "Notification");
                builder.setContentTitle("Device Notification");
                builder.setContentText("The device have stop from used");
                builder.setSmallIcon(R.drawable.stopwatch);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(spam.this);
                managerCompat.notify(1, builder.build());


                Toast.makeText(spam.this, "pulling device", Toast.LENGTH_SHORT).show();
            }
        });




    }





}





