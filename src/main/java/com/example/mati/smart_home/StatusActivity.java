package com.example.mati.smart_home;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import static java.lang.Boolean.TRUE;

public class StatusActivity extends AppCompatActivity {
    String requestUrl = "http://192.168.1.60";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
    }

    public void refr(View view) {

        new Thread(new Runnable() {
            TextView resp = findViewById(R.id.Object_status);
            TextView temp = findViewById(R.id.Temperature_header);
            TextView mois = findViewById(R.id.Moisture);

            public void run() {

                /*StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/stat",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response1) {
                                resp.setText("");
                                resp.setText("Status obiektu: " + response1);
                                Log.d(response1, "respw");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(error.toString(), "blad");
                    }
                });
                MySingleton.getInstance(StatusActivity.this).addToRequestQueue(stringRequest);

                StringRequest stringRequest_temp = new StringRequest(Request.Method.GET, requestUrl + "/temp",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response2) {
                                temp.setText("");
                                temp.setText("Temperatura: " + response2);
                                Log.d(response2, "respe");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(error.toString(), "blad");
                    }
                });
                MySingleton.getInstance(StatusActivity.this).addToRequestQueue(stringRequest_temp);
*/
                StringRequest stringRequest_mois = new StringRequest(Request.Method.GET, requestUrl + "/mois",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response3) {
                                mois.setText("");
                                mois.setText("Wilgotność: " + response3);
                                Log.d(response3, "respr");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(error.toString(), "blad");
                    }
                });
                MySingleton.getInstance(StatusActivity.this).addToRequestQueue(stringRequest_mois);
               /* if(mois.getText().equals(" ")){
                    MySingleton.getInstance(StatusActivity.this).addToRequestQueue(stringRequest_mois);
                }*/
               /* if(temp.getText().equals(" ")){
                    MySingleton.getInstance(StatusActivity.this).addToRequestQueue(stringRequest_temp);
                }
                if(resp.getText().equals(" ")){
                    MySingleton.getInstance(StatusActivity.this).addToRequestQueue(stringRequest);
                }*/
            }
        }).start();
    }
}
