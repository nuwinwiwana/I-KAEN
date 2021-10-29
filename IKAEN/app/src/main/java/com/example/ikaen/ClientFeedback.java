package com.example.ikaen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/html");
                i.putExtra(Intent.EXTRA_EMAIL,new String( "mharithzaeef@gmail.com"));
                i.putExtra(Intent.EXTRA_SUBJECT,"Feedback from App");
                i.putExtra(Intent.EXTRA_TEXT,"Name :"+editTextName.getText()+"\n Message:"+editTextFeedback.getText());
                try {
                    startActivity(Intent.createChooser(i,"Please Select Email"));


                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(ClientFeedback.this,"There are no email client",Toast.LENGTH_SHORT ).show();
                }
                }

        });
    }
}