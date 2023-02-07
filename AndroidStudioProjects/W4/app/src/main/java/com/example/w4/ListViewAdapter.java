package com.example.w4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class Restaurant{
    public String name;
    public int id;

    Restaurant(String name, int id){
        this.name = name;
        this.id = id;
    }
}


public class ListViewAdapter extends BaseAdapter {
    private ArrayList<Restaurant> items;
    private Context mContext;

    ListViewAdapter(ArrayList<Restaurant> items, Context mContext){
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater)  mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listview,viewGroup, false);
        }
        ImageView img = view.findViewById(R.id.imageView3);
        TextView text = view.findViewById(R.id.textView);

        text.setText(items.get(i).name);
        img.setImageResource(items.get(i).id);

        return view;
    }
}
