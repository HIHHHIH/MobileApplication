package com.example.w7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        button.setOnClickListener(view ->{
            OkHttpClient client = new OkHttpClient();

            DataModel data = new DataModel();
            data.setName("sam");
            data.setJob("programmer");

            Gson gson = new Gson();
            String json = gson.toJson(data, DataModel.class);

            HttpUrl.Builder urlBuilder = HttpUrl.parse("https://reqres.in/api/users").newBuilder();
            String url = urlBuilder.build().toString();

            textView1.setText(url);

            Request req = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json"),json)).build();

            client.newCall(req).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    final String myResponse = response.body().string();

                    Gson gson = new GsonBuilder().create();
                    final DataModel data1 = gson.fromJson(myResponse,  DataModel.class);

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView2.setText(myResponse);
                            Log.d("response1", data1.getJob());
                            Log.d("response2", data1.getName());
                            Log.d("response3", data1.getPer_page());
                            Log.d("response4", data1.getPage());
                        }
                    });

                }
            });
        });
    }
}