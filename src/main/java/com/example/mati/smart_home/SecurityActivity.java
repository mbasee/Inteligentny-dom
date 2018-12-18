package com.example.mati.smart_home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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
                                            }
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
    }
}
