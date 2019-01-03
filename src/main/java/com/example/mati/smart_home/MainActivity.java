package com.example.mati.smart_home;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
        Button log = findViewById(R.id.Zaloguj);
        if(LoginActivity.status == 0){
            log.setText("Wyloguj");
            //LoginActivity.status = 1;
        }
        else{
            log.setText("Zaloguj");
            LoginActivity.status = 1;
        }
    }
    public void przejscie_zaloguj(View view){
        if(LoginActivity.status == 0){
            LoginActivity.status = 1;
            Button log = findViewById(R.id.Zaloguj);
            log.setText("Zaloguj");
            HouseActivity.runT = false;
        }
        else {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void przejscie_info(View view) {
        Intent i = new Intent(this,InfoActivity.class);
        startActivity(i);
    }
    public void przejscie_house(View view) {
        if(LoginActivity.status == 0) {
            Intent i = new Intent(this, HouseActivity.class);
            startActivity(i);
        }
    }
}
