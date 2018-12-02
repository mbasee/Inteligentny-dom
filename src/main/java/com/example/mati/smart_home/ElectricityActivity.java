package com.example.mati.smart_home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ElectricityActivity extends AppCompatActivity {
    Switch Switch1;
    String requestUrl = "http://192.168.1.48";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);
        Switch1 = (Switch) findViewById(R.id.switch1);
        //Switch2 = (Switch) findViewById(R.id.simpleSwitch2);

        Switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // final RequestQueue queue = Volley.newRequestQueue(this);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/on",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //TextView resp = findViewById(R.id.Login_opis);
                                    //resp.setText(response);
                                    Log.d(response, "resp");
                                    Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_LONG).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(error.toString(), "blad");
                        }
                    });
                    //queue.add(stringRequest);
                    MySingleton.getInstance(ElectricityActivity.this).addToRequestQueue(stringRequest);


                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/off",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //TextView resp = findViewById(R.id.Login_opis);
                                    //resp.setText(response);
                                    Log.d(response, "resp");
                                    Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_LONG).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(error.toString(), "blad");
                        }
                    });
                    //queue.add(stringRequest);
                    MySingleton.getInstance(ElectricityActivity.this).addToRequestQueue(stringRequest);
                }
            }
        });

    }
    public void refresh(View view){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/ene",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(response, "resp");
                        System.out.println(response.getClass().getName());
                        if(response.equals("ON")){
                            Switch1.setChecked(TRUE);
                        }
                        else if(response.equals("OFF")){
                            Switch1.setChecked(FALSE);
                        }
                        Toast.makeText(getApplicationContext(), "Odświeżono", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(error.toString(), "blad");
            }
        });
        //queue.add(stringRequest);
        MySingleton.getInstance(ElectricityActivity.this).addToRequestQueue(stringRequest);
    }
}

