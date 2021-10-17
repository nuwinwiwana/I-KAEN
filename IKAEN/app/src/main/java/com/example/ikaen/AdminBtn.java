package com.example.ikaen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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


        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference storageReference = firebaseDatabase.getReference("Device");

                FeedbackDetails feedbackDetails = new FeedbackDetails(1);
                storageReference.setValue(feedbackDetails);

                Toast.makeText(AdminBtn.this, "device activating", Toast.LENGTH_SHORT).show();


            }

        });

        sendstopData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference storageReference = firebaseDatabase.getReference("Device");

                FeedbackDetails feedbackDetails = new FeedbackDetails(0);
                storageReference.setValue(feedbackDetails);


                Toast.makeText(AdminBtn.this, "pulling device", Toast.LENGTH_SHORT).show();
            }
        });

    }
}