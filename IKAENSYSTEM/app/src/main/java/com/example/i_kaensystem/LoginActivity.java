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

    private EditText Name,Email,Password;
    private Button Login;
    private TextView ForgotPassword,SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        setupUIView();



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }






    private void setupUIView(){
        Name =  (EditText)findViewById(R.id.editTextUsername);
        Email = (EditText)findViewById(R.id.editTextEmail);
        Password = (EditText)findViewById(R.id.editTextPassword);
        Login = (Button)findViewById(R.id.LoginButton);
        ForgotPassword = (TextView)findViewById(R.id.tvForgotPassword);
        SignUp =  (TextView)findViewById(R.id.tvSignUp);


    }
    private Boolean validate() {
        Boolean result = false;

        String name = Name.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        if (name.isEmpty() && password.isEmpty() && email.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();

        }else{
            result = true;

        }
        return result;

    }
}