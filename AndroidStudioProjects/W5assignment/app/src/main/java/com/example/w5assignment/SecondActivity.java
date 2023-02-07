package com.example.w5assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    String time;
    int hour;
    int minute;
    String msg;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        time = intent.getStringExtra("time");
        hour= Integer.parseInt(time.split(":")[0]);
        minute = Integer.parseInt(time.split(":")[1]);
        desc = intent.getStringExtra("desc");
        msg = "Do you want to set alarm at "+time+" ?";
        TextView textView = findViewById(R.id.textView2);
        textView.setText(msg);

    }

    public void setAlarm(View view) {

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_HOUR,hour)
                .putExtra(AlarmClock.EXTRA_MINUTES,minute)
                .putExtra(AlarmClock.EXTRA_MESSAGE,desc);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }

    public void close(View view) {
        this.finish();
    }
}