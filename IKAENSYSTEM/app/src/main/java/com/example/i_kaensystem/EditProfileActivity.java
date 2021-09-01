package com.example.i_kaensystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfileActivity extends AppCompatActivity {

        private EditText newUserName;
        private TextView newUserEmail;
        private Button save;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.edit_profile_page);

                newUserName = findViewById(R.id.etNameUpdate);
                newUserEmail = findViewById(R.id.tvEmail);
                save = findViewById(R.id.btnSave);


        }



}




