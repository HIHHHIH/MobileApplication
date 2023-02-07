package com.example.w3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int i=0;
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.button_food);
        Button btn2 = (Button) findViewById(R.id.button_bottom);
        TextView text = (TextView) findViewById(R.id.textView);

        final ImageView img1 = (ImageView) findViewById(R.id.imageView);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=(i+1)%3;
                if(i==0){
                    img1.setImageResource(R.drawable.chicken);
                }
                if(i==1)
                        img1.setImageResource(R.drawable.pizza);
                if(i==2)
                    img1.setImageResource(R.drawable.food3);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j=1-j;
                if(j==0) text.setText("조용현");
                if(j==1) text.setText("2013311011");
            }
        });
    }
}