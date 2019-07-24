package com.example.myapplicationaccountenrichmentapp;

import android.os.AsyncTask;

import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class HttpPostTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("");
            File file = new File(strings[0]);
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

                    String line = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((line = responseStreamReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    responseStreamReader.close();

                    String response = stringBuilder.toString();
                    return response;
                case 301:
                case 302:
                case 307:
                    //handle redirect - for example, re-post to the new location
                    break;
                default:
                    //do something sensible
            }
        } catch (Exception ex) {

        }
        return "";
    }
}
