package com.example.ikaen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class adminSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_setting);

        final LinearLayout textView3 = findViewById(R.id.linearLayoutSetting1);
        final CardView c2 = findViewById(R.id.settingCard2);
        final CardView c3 = findViewById(R.id.settingCard3);
        final CardView c4 = findViewById(R.id.settingCard4);
        final CardView c5 = findViewById(R.id.settingCard5);
        final CardView logout = findViewById(R.id.logout);

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminSetting.this,ViewProfileAdmin.class));
            }
        });


        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminSetting.this,AdminWeatherInfo.class));
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminSetting.this,Aboutus.class));
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminSetting.this,AdminActivityHistory.class));
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminSetting.this,AdminUserList.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminSetting.this,AdminOrClient.class));
            }
        });
    }
}