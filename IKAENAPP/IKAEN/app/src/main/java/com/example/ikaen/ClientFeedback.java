package com.example.ikaen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClientFeedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_feedback);
        final EditText editTextName = findViewById(R.id.etName);
        final EditText editTextFeedback = findViewById(R.id.etFeedback);
        Button SendButton = findViewById(R.id.BtnSend);

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ikaenapp@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from App");
                intent.putExtra(Intent.EXTRA_TEXT,"Name :"+editTextName.getText()+"\n Message:"+editTextFeedback.getText());
                intent.setPackage("com.google.android.gm");

                try {
                    startActivity(Intent.createChooser(intent, null));


                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(ClientFeedback.this,"There are no email client",Toast.LENGTH_SHORT ).show();
                }
                }

        });
    }
}