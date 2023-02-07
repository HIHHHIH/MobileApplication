package com.example.w5assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static final String TIME = "time";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String DESC = "desc";


    public void getAlarmInfo(View view) {
        EditText editTime = findViewById(R.id.editTextTime);
        EditText editDesc = findViewById(R.id.editDescription);

        String time = editTime.getText().toString();

        String desc = editDesc.getText().toString();

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra("time",time);
        intent.putExtra("desc", desc);
        startActivity(intent);
    }
}