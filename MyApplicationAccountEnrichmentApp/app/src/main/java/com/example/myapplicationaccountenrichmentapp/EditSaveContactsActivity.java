package com.example.myapplicationaccountenrichmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.actit.models.Contact;
import com.example.actit.models.ContactNumber;
import com.example.actit.models.ImageScanResult;
import com.example.actit.models.Status;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class EditSaveContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_save_contacts);

        Bundle b = getIntent().getExtras();
        String response = null; // or other values
        if (b != null)
            response = b.getString("key");

        if (response != null && !response.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();

            try {
                ImageScanResult result = mapper.readValue(response, ImageScanResult.class);
                if (result.Status == Status.Success) {
                    Contact contact = result.ContactDetails.get(0);
                    EditText name = findViewById(R.id.editText13);
                    name.setText(contact.Name);
                    EditText designation = findViewById(R.id.editText14);
                    designation.setText(contact.JobTitle);
                    EditText company = findViewById(R.id.editText16);
                    company.setText(contact.Company);
                    EditText phones = findViewById(R.id.editText15);
                    if (contact.ContactNumbers != null)
                        for (ContactNumber phone : contact.ContactNumbers
                        ) {
                            phones.append(phone.Number);
                        }
                    EditText emails = findViewById(R.id.editText18);
                    if (contact.EmailAddresses != null)
                        for (String email : contact.EmailAddresses
                        ) {
                            emails.append(email);
                        }
                    EditText addresses = findViewById(R.id.editText19);
                    if (contact.AddressLines != null)
                        for (String address : contact.AddressLines
                        ) {
                            addresses.append(address);
                        }
                    EditText websites = findViewById(R.id.editText17);
                    if (contact.Websites != null)
                        for (String site : contact.Websites
                        ) {
                            websites.append(site);
                        }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void goToContactList(View view) {
        Intent intent = new Intent(EditSaveContactsActivity.this, Contactlist.class);
        startActivity(intent);
        finish();
    }
}
