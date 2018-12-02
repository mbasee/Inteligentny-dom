package com.example.mati.smart_home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    String requestUrl = "http://192.168.1.48/haslo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void przejscie_loguj(View view) {
        //sprawdzenie poprawnosci logowania
        EditText login = findViewById(R.id.Login);
        EditText password = findViewById(R.id.Password);
        //Intent i = new Intent(this,HouseActivity.class);
        //startActivity(i);
        //finish();
        final RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        TextView resp = findViewById(R.id.Login_opis);
                        resp.setText(response);
                        Log.d(response, "resp");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(error.toString(), "blad");
            }
        })
        {
        @Override
        protected Map<String, String> getParams () throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("oauth", "jsH");
            return params;
        }
    };
        //queue.add(stringRequest);
        MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);

        Intent i = new Intent(this,HouseActivity.class);
        startActivity(i);
        finish();
    }

}
