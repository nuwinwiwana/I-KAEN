package com.example.ikaen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {



            @Override

            public void run() {

                Intent i = new Intent(splashScreen.this, AdminOrClient.class);

                startActivity(i);



                finish();

            }

        }, 3000);
    }
}