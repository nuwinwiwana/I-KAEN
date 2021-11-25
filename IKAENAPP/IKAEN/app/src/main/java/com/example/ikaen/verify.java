package com.example.ikaen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class verify extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    boolean _once = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

       Button verifyBtn = findViewById(R.id.button);

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             verify();
             if (!_once){

                 _once = true;
                 Timer t = new Timer();
                 TimerTask verifyCheck= new TimerTask() {
                     public void run() {

                         //perform your action here
                         user.reload();
                         if(user.isEmailVerified()){

                             t.cancel();
                             t.purge();
                             finish();
                         }
                     }
                 };
                 t.schedule(verifyCheck,1, 1000);
             }
            }
        });

    }

    private void verify (){

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });

    }
}