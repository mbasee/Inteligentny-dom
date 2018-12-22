package com.example.mati.smart_home;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Thread.*;

public class SecurityActivity extends AppCompatActivity {
    int count = 0;
    public String requestUrl = "http://192.168.1.60";
    Thread t;
    private boolean runningThread = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        final TextView text = findViewById(R.id.textView3);
        text.setTextColor(Color.parseColor("#ff0d1d"));
        text.setText("Wykryto ruch!");
        /*new Thread(new Runnable() {
            public void run() {

                try {
                    URL url = new URL("https://192.168.1.60");
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    String urlParameters = "m=eler";
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("User-Agent", "Dalvik/2.1.0");
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("Accept-Encoding", "gzip");

                    connection.setDoOutput(true);
                    DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                    dStream.writeBytes(urlParameters);
                    dStream.flush();
                    dStream.close();
                    int responseCode = connection.getResponseCode();
                    Log.d(Integer.toString(responseCode), "resp");
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = "";
                    StringBuilder responseOutput = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        responseOutput.append(line);
                    }
                    br.close();
                    text.setText(responseOutput);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }}).start();



        /*
         t = new Thread(){
            public void run(){
                while(!isInterrupted()){
                    try{
                        sleep(3000);
                        if (!runningThread) {
                            return;}
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                count++;

                                StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/eler",
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                Log.d(response, "resp");
                                                text.setText(String.valueOf(count));
                                                /*if (response.substring(0, 1).equals("1")) {
                                                    Switch1.setChecked(TRUE);
                                                } else if (response.substring(0, 1).equals("0")) {
                                                    Switch1.setChecked(FALSE);
                                                }
                                                if (response.substring(1, 2).equals("1")) {
                                                    Switch2.setChecked(TRUE);
                                                } else if (response.substring(1, 2).equals("0")) {
                                                    Switch2.setChecked(FALSE);
                                                }*/
               /*                             }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), "Błąd komunikacji", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                MySingleton.getInstance(SecurityActivity.this).addToRequestQueue(stringRequest);
                            }
                        });
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
    public void onBackPressed(){

        runningThread = false;
        this.finish();
    */}
}
