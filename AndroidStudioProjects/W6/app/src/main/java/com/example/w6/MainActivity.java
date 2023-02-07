package com.example.w6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView1, textView2;
    CustomHandler handler = new CustomHandler();
    CustomHandler handler2 =  new CustomHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomThread runnable = new CustomThread();
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> {
            new Thread(runnable).start();
        });


    }

    class CustomThread implements Runnable{
        @Override
        public void run(){
            Bundle bundle = new Bundle();
            bundle.putString("value", "Waiting Start");
            Message msg = new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);

            synchronized (this){
                try{
                    wait(3000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            bundle = new Bundle();
            bundle.putString("value", "Waiting Finish");
            msg = new Message();
            msg.setData(bundle);
            handler2.sendMessage(msg);
        }
    }

    class CustomHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg){
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String value = bundle.getString("value");
            textView1.setText("First Handler" + value);
        }
    }

    class CustomHandler2 extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg){
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String value = bundle.getString("value");
            textView2.setText("Second Handle" + value);
        }
    }

}