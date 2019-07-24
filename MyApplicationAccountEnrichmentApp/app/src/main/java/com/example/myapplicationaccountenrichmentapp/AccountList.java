package com.example.myapplicationaccountenrichmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountList extends AppCompatActivity {

    TextView accolite,accenture;
    Button b1,b2;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountlist);

        accolite = findViewById(R.id.textView10);
        accenture = findViewById(R.id.textView9);
        b1 = findViewById(R.id.button2);

        accolite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountList.this,Contactlist.class);
                AccountList.this.startActivity(i);
            }
        });

        accenture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AccountList.this, Contactlist.class);
                AccountList.this.startActivity(it);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountList.this, AddContactActivity.class);
                AccountList.this.startActivity(i);
            }
        });

    }
}
