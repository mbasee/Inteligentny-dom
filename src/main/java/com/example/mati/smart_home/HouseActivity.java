package com.example.mati.smart_home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HouseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
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
    }
}
