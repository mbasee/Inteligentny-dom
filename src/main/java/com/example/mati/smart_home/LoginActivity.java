package com.example.mati.smart_home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
    String requestUrl = "https://serwer1952434.home.pl/login.php";
    String loginn, passwordd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void przejscie_loguj(View view) {
        //sprawdzenie poprawnosci logowania
        EditText login = findViewById(R.id.Login);
        EditText password = findViewById(R.id.Password);
        loginn = login.getText().toString();
        passwordd = password.getText().toString();
        Log.d(loginn,"login");
        Log.d(passwordd,"pass");

        if(loginn.equals("") || passwordd.equals("")){
            Toast.makeText(getApplicationContext(), "Popraw dane", Toast.LENGTH_SHORT).show();
            login.setText("");
            password.setText("");
        }
        else {
            //final RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl,
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
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", loginn);
                    params.put("password", passwordd);
                    return params;
                }
            };
            //queue.add(stringRequest);
            MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
        }
        //Intent i = new Intent(this,HouseActivity.class);
        //startActivity(i);
        //finish();
    }

}
