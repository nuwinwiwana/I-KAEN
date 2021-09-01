package com.example.i_kaensystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText userName,userEmail,userPassword;
    private Button buttonLogin;
    private TextView ForgotPassword,SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        setupUIView();



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                startActivity(intent);
                if (validate()) {

                }
            }
        });

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);

    }

     });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);

            }

        });

    }






    private void setupUIView(){
        userName = (EditText)findViewById(R.id.editTextUsername);
        userEmail = (EditText)findViewById(R.id.editTextEmail);
        userPassword = (EditText)findViewById(R.id.editTextPassword);
        buttonLogin = (Button)findViewById(R.id.LoginButton);
        ForgotPassword = (TextView)findViewById(R.id.tvForgotPassword);
        SignUp = (TextView)findViewById(R.id.tvSignUp);


    }
    private Boolean validate() {
        Boolean result = false;

        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String password = userEmail.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();

        }else{
            result = true;

        }
        return result;

    }
}