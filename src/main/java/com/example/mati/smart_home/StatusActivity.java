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


public class StatusActivity extends AppCompatActivity {
    Thread tStat;
    private boolean runT = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        final TextView resp = findViewById(R.id.Object_status);
        final TextView temp = findViewById(R.id.Temperature_header);
        final TextView mois = findViewById(R.id.Moisture);

        tStat = new Thread() {
            public void run() {
                while (!isInterrupted()) {
                    try {
                        tStat.sleep(2500);
                        if (!runT) {
                            return;
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, HouseActivity.requestUrl + "/mois",
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response3) {
                                                if(response3.length()==6 && response3.endsWith(" ")){
                                                    temp.setText("");
                                                    temp.setText("Temperatura: " + response3.substring(0,2));
                                                    mois.setText("");
                                                    mois.setText("Wilgotność: " + response3.substring(2,4));
                                                    if(response3.substring(4,5).equals("1")){
                                                        resp.setText("");
                                                        resp.setTextColor(Color.parseColor("#00a000"));
                                                        resp.setText("Status obiektu: Zabezpieczony");
                                                    }
                                                    else{
                                                        resp.setText("");
                                                        resp.setTextColor(Color.parseColor("#ff0d1d"));
                                                        resp.setText("Status obiektu: Odbezpieczony");
                                                    }
                                                }
                                                else if(response3.length()==5 && response3.endsWith(" ")){
                                                    temp.setText("");
                                                    temp.setText("Temperatura: " + response3.substring(0,1));
                                                    mois.setText("");
                                                    mois.setText("Wilgotność: " + response3.substring(1,3));
                                                    if(response3.substring(3,4).equals("1")){
                                                        resp.setText("");
                                                        resp.setTextColor(Color.parseColor("#00a000"));
                                                        resp.setText("Status obiektu: Zabezpieczony");
                                                    }
                                                    else{
                                                        resp.setText("");
                                                        resp.setTextColor(Color.parseColor("#ff0d1d"));
                                                        resp.setText("Status obiektu: Odbezpieczony");
                                                    }
                                                }
                                                else if(response3.length()==5 && !response3.endsWith(" ")){
                                                    temp.setText("");
                                                    temp.setText("Temperatura: " + response3.substring(0,2));
                                                    mois.setText("");
                                                    mois.setText("Wilgotność: " + response3.substring(2,4));
                                                    if(response3.substring(4,5).equals("1")){
                                                        resp.setText("");
                                                        resp.setTextColor(Color.parseColor("#00a000"));
                                                        resp.setText("Status obiektu: Zabezpieczony");
                                                    }
                                                    else{
                                                        resp.setText("");
                                                        resp.setTextColor(Color.parseColor("#ff0d1d"));
                                                        resp.setText("Status obiektu: Odbezpieczony");
                                                    }
                                                }
                                                else{
                                                    temp.setText("");
                                                    temp.setText("Temperatura: " + response3.substring(0,1));
                                                    mois.setText("");
                                                    mois.setText("Wilgotność: " + response3.substring(1,3));
                                                    if(response3.substring(3,4).equals("1")){
                                                        resp.setText("");
                                                        resp.setText("Status obiektu: Zabezpieczony");
                                                    }
                                                    else{
                                                        resp.setText("");
                                                        resp.setText("Status obiektu: Odbezpieczony");
                                                    }
                                                }
                                                Log.d(response3, "respr");
                                            }

                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), "Błąd komunikacji", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                ;
                                MySingleton.getInstance(StatusActivity.this).addToRequestQueue(stringRequest);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        tStat.start();
    }
    public void onBackPressed(){
        runT = false;
        this.finish();
    }


   /* public void refr(View view) {

        new Thread(new Runnable() {
            TextView resp = findViewById(R.id.Object_status);
            TextView temp = findViewById(R.id.Temperature_header);
            TextView mois = findViewById(R.id.Moisture);

            public void run() {

                StringRequest stringRequest_mois = new StringRequest(Request.Method.GET, requestUrl + "/mois",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response3) {

                                if(response3.length()==6 && response3.endsWith(" ")){
                                temp.setText("");
                                temp.setText("Temperatura: " + response3.substring(0,2));
                                mois.setText("");
                                mois.setText("Wilgotność: " + response3.substring(2,4));
                                    if(response3.substring(4,5).equals("1")){
                                        resp.setText("");
                                        resp.setTextColor(Color.parseColor("#00a000"));
                                        resp.setText("Status obiektu: Zabezpieczony");
                                    }
                                    else{
                                        resp.setText("");
                                        resp.setTextColor(Color.parseColor("#ff0d1d"));
                                        resp.setText("Status obiektu: Odbezpieczony");
                                    }
                                }
                                else if(response3.length()==5 && response3.endsWith(" ")){
                                    temp.setText("");
                                    temp.setText("Temperatura: " + response3.substring(0,1));
                                    mois.setText("");
                                    mois.setText("Wilgotność: " + response3.substring(1,3));
                                    if(response3.substring(3,4).equals("1")){
                                        resp.setText("");
                                        resp.setTextColor(Color.parseColor("#00a000"));
                                        resp.setText("Status obiektu: Zabezpieczony");
                                    }
                                    else{
                                        resp.setText("");
                                        resp.setTextColor(Color.parseColor("#ff0d1d"));
                                        resp.setText("Status obiektu: Odbezpieczony");
                                    }
                                }
                                else if(response3.length()==5 && !response3.endsWith(" ")){
                                    temp.setText("");
                                    temp.setText("Temperatura: " + response3.substring(0,2));
                                    mois.setText("");
                                    mois.setText("Wilgotność: " + response3.substring(2,4));
                                    if(response3.substring(4,5).equals("1")){
                                        resp.setText("");
                                        resp.setTextColor(Color.parseColor("#00a000"));
                                        resp.setText("Status obiektu: Zabezpieczony");
                                    }
                                    else{
                                        resp.setText("");
                                        resp.setTextColor(Color.parseColor("#ff0d1d"));
                                        resp.setText("Status obiektu: Odbezpieczony");
                                    }
                                }
                                else{
                                    temp.setText("");
                                    temp.setText("Temperatura: " + response3.substring(0,1));
                                    mois.setText("");
                                    mois.setText("Wilgotność: " + response3.substring(1,3));
                                    if(response3.substring(3,4).equals("1")){
                                        resp.setText("");
                                        resp.setText("Status obiektu: Zabezpieczony");
                                    }
                                    else{
                                        resp.setText("");
                                        resp.setText("Status obiektu: Odbezpieczony");
                                    }
                                }
                                Log.d(response3, "respr");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(error.toString(), "blad");
                        Toast.makeText(getApplicationContext(), "Błąd komunikacji", Toast.LENGTH_SHORT).show();

                    }
                });
                MySingleton.getInstance(StatusActivity.this).addToRequestQueue(stringRequest_mois);
            }
        }).start();
    }
*/
}
