package com.example.myapplicationaccountenrichmentapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class AddContactActivity extends AppCompatActivity {

    ImageButton bCameraOCR, bPhone, bExcel;
    String currentImagePath = null;
    private static final int IMAGE_REQUEST = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_activity);

        bCameraOCR = findViewById(R.id.scanOCR);
        bPhone = findViewById(R.id.dog);
        bExcel = findViewById(R.id.imageButton2);

        bPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });


//        bExcel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (imagepath != null) {
//                    new UploadFileToServer().execute();
//                }else{
//                    Toast.makeText(getApplicationContext(), "Please select a file to upload.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            //String result = new HttpPostTask().execute(currentImagePath).get();
            String result = ScanImage();
            Intent intent = new Intent(AddContactActivity.this, EditSaveContactsActivity.class);
            Bundle b = new Bundle();
            b.putString("key", result); //Your id
            intent.putExtras(b); //Put your id to your next Intent
            startActivity(intent);
            finish();
            //AddContactActivity.this.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scanContactImage(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;
            try {
                imageFile = getImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (imageFile != null) {
                Uri imageUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(cameraIntent, IMAGE_REQUEST);
            }
        }
    }

    private File getImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "jpg_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(imageName, ".jpg", storageDir);
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    private String ScanImage(){
        try {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL("https://contactscanner.azurewebsites.net/CardScanner/ScanCard");
            File file = new File(currentImagePath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

//            String auth = "Bearer " + oauthToken;
//            connection.setRequestProperty("Authorization", basicAuth);

            String boundary = UUID.randomUUID().toString();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            DataOutputStream request = new DataOutputStream(connection.getOutputStream());

//            request.writeBytes("--" + boundary + "\r\n");
//            request.writeBytes("Content-Disposition: form-data; name=\"description\"\r\n\r\n");
//            request.writeBytes(fileDescription + "\r\n");

            request.writeBytes("--" + boundary + "\r\n");
            request.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n\r\n");
            request.write(FileUtils.readFileToByteArray(file));
            request.writeBytes("\r\n");

            request.writeBytes("--" + boundary + "--\r\n");
            request.flush();
            int respCode = connection.getResponseCode();

            switch (respCode) {
                case 200:
                    InputStream responseStream = new
                            BufferedInputStream(connection.getInputStream());
                    BufferedReader responseStreamReader =
                            new BufferedReader(new InputStreamReader(responseStream));

                    String line;
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((line = responseStreamReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    responseStreamReader.close();

                    return stringBuilder.toString();
                case 301:
                case 302:
                case 307:
                    //handle redirect - for example, re-post to the new location
                    break;
                default:
                    //do something sensible
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
