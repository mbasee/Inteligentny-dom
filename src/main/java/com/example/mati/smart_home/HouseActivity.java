package com.example.mati.smart_home;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class HouseActivity extends AppCompatActivity {
    Thread tStat;
    private boolean runT = true;
    String requestUrl = "http://192.168.1.60";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
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
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl + "/alarm",
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                if(response.substring(0,1).equals("1")){
                                                    Log.d(response.substring(0,1),"alarm");
                                                    //temp.setText("");
                                                    createNotification();
                                                }
                                                Log.d(response,"home");
                                            }


                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), "Błąd komunikacji", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                        ;
                                MySingleton.getInstance(HouseActivity.this).addToRequestQueue(stringRequest);
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
    //public void onBackPressed(){

        //runT = false;
        //this.finish();
        //super.onBackPressed();
    //}
    //
    private void createNotification() {

            Intent intent = new Intent(this, SecurityActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.secur);

            Notification noti = new NotificationCompat.Builder(this)
                    .setContentTitle("Alarm")
                    .setContentText("Wykryto ruch w obiekcie")
                    .setTicker("Alarm obiektu")
                    .setSmallIcon(android.R.drawable.ic_dialog_alert)
                    .setLargeIcon(icon)
                    .setAutoCancel(true)
                    .setContentIntent(pIntent)
                    .build();

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.notify(0, noti);
        }
    public void przejscie_status(View view){
        Intent i = new Intent(this,StatusActivity.class);
        startActivity(i);
    }

    public void przejscie_energia(View view){
        Intent i = new Intent(this,ElectricityActivity.class);
        startActivity(i);
    }

    public void przejscie_alarm(View view){
        Intent i = new Intent(this,SecurityActivity.class);
        startActivity(i);
        //finish();
    }
}
