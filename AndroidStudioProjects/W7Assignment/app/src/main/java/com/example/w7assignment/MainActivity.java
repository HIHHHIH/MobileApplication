package com.example.w7assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView textView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(view ->{
            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("http://www.omdbapi.com/?apikey=ad9c19f &").newBuilder();

            String search = editText.getText().toString();
            urlBuilder.addQueryParameter("t",search);
            String url = urlBuilder.build().toString();

            textView.setText(url);

            Request req = new Request.Builder().url(url).build();

            client.newCall(req).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    final String myResponse = response.body().string();
                    Gson gson = new GsonBuilder().create();
                    final MovieInfo movieInfo = gson.fromJson(myResponse, MovieInfo.class);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String text =
                                    "Title : ".concat(movieInfo.getTitle()).concat("\n")
                                            .concat("Year : ").concat(movieInfo.getYear()).concat("\n")
                                            .concat("Released Date : ").concat(movieInfo.getReleased()).concat("\n")
                                            .concat("Run time : ").concat(movieInfo.getRuntime()).concat("\n")
                                            .concat("Director : ").concat(movieInfo.getDirector()).concat("\n")
                                            .concat("Genre : ").concat(movieInfo.getGenre()).concat("\n")
                                            .concat("IMDB Rating : ").concat(movieInfo.getImdbRating()).concat("\n")
                                            .concat("Meta Score : ").concat(movieInfo.getMetascore()).concat("\n");
                            textView.setText(text);
                        }
                    });

                }
            });
        });

    }
}