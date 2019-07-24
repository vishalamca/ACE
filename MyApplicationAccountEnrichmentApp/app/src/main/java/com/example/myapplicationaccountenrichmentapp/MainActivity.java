package com.example.myapplicationaccountenrichmentapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bSubmit;
    EditText eEmail, ePassword;
    int counter=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bSubmit = findViewById(R.id.button);
        eEmail = findViewById(R.id.editText);
        ePassword = findViewById(R.id.editText2);

        bSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(eEmail.getText().toString().equals("admin") && ePassword.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, AccountList.class);
                    //getApplicationContext().startActivity(i);
                    MainActivity.this.startActivity(i);
                }
                else{
                    eEmail.setBackgroundColor(Color.RED);
                    ePassword.setBackgroundColor(Color.RED);
                    counter--;
                    if(counter==0){
                        Toast.makeText(getApplicationContext(),"Too many tries. Try again later", Toast.LENGTH_SHORT);
                        bSubmit.setEnabled(false);
                    }
                }
            }
        });
    }
}
