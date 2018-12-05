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
    Switch Switch1, Switch2;
    public String requestUrl = "http://192.168.1.60";
    int refresh_temp = 1;
    String[] refresh_name = {"/RL1", "/RL2"};
    Switch[] refresh_switch = new Switch[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);
        Switch1 = (Switch) findViewById(R.id.switch1);
        Switch2 = (Switch) findViewById(R.id.switch2);
        refresh_switch[0] = Switch1;
        refresh_switch[1] = Switch2;

        Switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/K_on",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d(response, "resp");
                                    Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(error.toString(), "blad");
                            Switch1.setChecked(FALSE);
                            MySingleton.getInstance(ElectricityActivity.this).getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
                                @Override
                                public boolean apply(Request<?> request) {
                                    return true;
                                }});
                        }
                    });
                    //queue.add(stringRequest);
                    MySingleton.getInstance(ElectricityActivity.this).addToRequestQueue(stringRequest);
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/K_off",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d(response, "resp");
                                    Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(error.toString(), "blad");
                            Switch1.setChecked(TRUE);
                            MySingleton.getInstance(ElectricityActivity.this).getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
                                @Override
                                public boolean apply(Request<?> request) {
                                    return true;
                                }});

                        }
                    });
                    //queue.add(stringRequest);
                    MySingleton.getInstance(ElectricityActivity.this).addToRequestQueue(stringRequest);
                }
            }
        });

        Switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/H_on",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d(response, "resp");
                                    Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(error.toString(), "blad");
                            Switch2.setChecked(FALSE);
                            MySingleton.getInstance(ElectricityActivity.this).getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
                                @Override
                                public boolean apply(Request<?> request) {
                                    return true;
                                }});
                        }
                    });
                    //queue.add(stringRequest);
                    MySingleton.getInstance(ElectricityActivity.this).addToRequestQueue(stringRequest);
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/H_off",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d(response, "resp");
                                    Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(error.toString(), "blad");
                            Switch2.setChecked(TRUE);
                            MySingleton.getInstance(ElectricityActivity.this).getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
                                @Override
                                public boolean apply(Request<?> request) {
                                    return true;
                                }});
                        }
                    });
                    //queue.add(stringRequest);
                    MySingleton.getInstance(ElectricityActivity.this).addToRequestQueue(stringRequest);
                }
            }
        });

    }
    public void refresh(View view){

            StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/RL1",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(response, "resp");
                        if(response.equals("1")){
                            Switch1.setChecked(TRUE);
                            //Switch1.setChecked(TRUE);
                        }
                        else if(response.equals("0")){
                            Switch1.setChecked(FALSE);
                        }
                        //Toast.makeText(getApplicationContext(), "Odświeżono", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(error.toString(), "blad");
            }
        });
        //queue.add(stringRequest);
        MySingleton.getInstance(ElectricityActivity.this).addToRequestQueue(stringRequest);

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, requestUrl + "/RL2",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(response, "resp");
                        if(response.equals("1")){
                            Switch2.setChecked(TRUE);
                            //Switch1.setChecked(TRUE);
                        }
                        else if(response.equals("0")){
                            Switch2.setChecked(FALSE);
                        }
                        //Toast.makeText(getApplicationContext(), "Odświeżono", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(error.toString(), "blad");
            }
        });
        //queue.add(stringRequest);
        MySingleton.getInstance(ElectricityActivity.this).addToRequestQueue(stringRequest1);
    }}


