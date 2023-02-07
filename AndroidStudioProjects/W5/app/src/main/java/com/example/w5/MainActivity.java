package com.example.w5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static final String EXT_NAME = "Name";
    public static final String EXT_SID = "StudentId";

    public void startNewActivity(View v){
        EditText editText = findViewById(R.id.edittext_url);
        Uri uri = Uri.parse("https://" + editText.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "unable to resolve activity '" + intent.getData().toString() + "'",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public static final String INTENT_ACTION = "edu.skku.cs.week5.hi";
    public void sendBroadcast(View view) {

        Intent intent = new Intent(INTENT_ACTION);
        sendBroadcast(intent);
    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getAction(),Toast.LENGTH_SHORT).show();
        }


    };
    @Override
    protected void onResume(){
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(INTENT_ACTION);
        registerReceiver(br,intentFilter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(br);
    }
}