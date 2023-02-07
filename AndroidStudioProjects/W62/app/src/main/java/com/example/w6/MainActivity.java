package com.example.w6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    CustomHandler handler = new CustomHandler();
    CustomHandler handler2 = new CustomHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomThread runnable = new CustomThread();
        textView = findViewById(R.id.textView);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> {
            new Thread(runnable).start();
        });
    }

    class CustomThread implements Runnable{
        @Override
        public void run(){
            double inside=0;
            double outside=0;
            double x, y;
            double dist;
            double pi=0;

            Bundle bundle = new Bundle();
            Message msg = new Message();

            for(int i=0;i<100;i++){
                synchronized (this) {
                    try{
                        for (int trials=0;trials < 1000000;trials++) {
                            x = Math.random();
                            y = Math.random();
                            dist = x*x + y*y;
                            if (dist <= 1) {
                                inside += 1;}
                            else outside +=1;
                        }
                        pi = 4 * inside / (inside+outside);
                    }catch(Exception e){
                        bundle = new Bundle();
                        msg = new Message();
                        bundle.putString("error", "something's wrong");
                        msg.setData(bundle);
                        handler2.sendMessage(msg);}

                }
                bundle = new Bundle();
                msg = new Message();

                bundle.putString("pi", Double.toString(pi));
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }
    }
    class CustomHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg){
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String value = bundle.getString("pi");
            textView.setText("estimated pi value: " + value);
        }
    }

    class CustomHandler2 extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg){
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String value = bundle.getString("error");
            textView.setText(value);
        }
    }
}