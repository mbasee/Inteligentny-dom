package com.example.mati.smart_home;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void przejscie_zaloguj(View view){
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    public void przejscie_info(View view) {
        //Uri uri = Uri.parse("http://google.pl");
        //Intent i = new Intent(Intent.ACTION_VIEW,uri);
        //Intent i = new Intent(this,InfoActivity.class);
        //startActivity(i);
    }
}
