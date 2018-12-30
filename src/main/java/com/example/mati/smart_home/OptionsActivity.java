package com.example.mati.smart_home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        EditText opcje = findViewById(R.id.opt);
        opcje.setText(HouseActivity.requestUrl);

    }
     public void save(View view){
         EditText opcje = findViewById(R.id.opt);
        HouseActivity.requestUrl=opcje.getText().toString();
        Log.d(HouseActivity.requestUrl.getClass().getName(),"coto");
     }
}
