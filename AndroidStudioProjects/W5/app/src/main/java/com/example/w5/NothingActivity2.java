package com.example.w5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class NothingActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nothing2);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXT_NAME);
        int sid = intent.getIntExtra(MainActivity.EXT_SID,0);

        Toast.makeText(getApplicationContext(),"Welcome,"+name+"(" + sid + ")!",Toast.LENGTH_SHORT).show();
    }
}