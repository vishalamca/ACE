package com.example.myapplicationaccountenrichmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Contactlist extends AppCompatActivity {

    Button add;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactlist);

        b2 = findViewById(R.id.button4);
        add = findViewById(R.id.button3);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Contactlist.this, AddContactActivity.class);
                //getApplicationContext().startActivity(i);
                Contactlist.this.startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Contactlist.this, ExploreContact.class);
                Contactlist.this.startActivity(it);
            }
        });

    }
}
