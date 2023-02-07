package com.example.w4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private ArrayList<Restaurant> items;
    private ListViewAdapter listviewadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.linearlayout2);
        Button btn = findViewById(R.id.button4);

        btn.setOnClickListener(view -> {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutInflater.inflate(R.layout.sub_layout,linearLayout,true);

            ImageView img1 = findViewById(R.id.imageView);
            ImageView img2 = findViewById(R.id.imageView2);

            img1.setImageResource(R.drawable.bbq);
            img2.setImageResource(R.drawable.bhc);
        });

        listview = findViewById(R.id.listview1);
        items = new ArrayList<Restaurant>();
        items.add(new Restaurant("도미노핏자",R.drawable.domino));
        items.add(new Restaurant("핏자헛",R.drawable.pizzahut));
        items.add(new Restaurant("핏자나라",R.drawable.pizzanarachickengongju));
        listviewadapter = new ListViewAdapter(items, getApplicationContext());

        listview.setAdapter(listviewadapter);
    }
}