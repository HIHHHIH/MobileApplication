package edu.skku.cs.w13;

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

            HttpUrl.Builder urlBuilder = HttpUrl.parse("https://vzh2kpy25l.execute-api.ap-northeast-2.amazonaws.com/dev/access").newBuilder();

            String name = editText.getText().toString();
            urlBuilder.addQueryParameter("name",name);
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
                    askWeather(myResponse);



                }
            });
        });
    }

    void askWeather(String city){
        String weather;
        OkHttpClient client = new OkHttpClient();

        //HttpUrl.Builder urlBuilder = HttpUrl.parse("api.weatherstack.com/current?access_key=bc645f68c02d082fcd9f4520e695e5f5").newBuilder();

        String name = editText.getText().toString();
        //urlBuilder.addQueryParameter("query",city);
        //String url = urlBuilder.build().toString();
        String url = "http://api.weatherstack.com/current?access_key=bc645f68c02d082fcd9f4520e695e5f5" + "&query="+city;
        Log.d("tag",url);

        Request req = new Request.Builder().url(url).build();

        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String myResponse = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(myResponse);
                    }
                });

            }
        });
    }
}