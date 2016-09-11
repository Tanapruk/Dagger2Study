package com.tanapruk.dagger2study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buildClient() {


        int cacheSize = 10 * 1024 * 1024;

        Cache cache = new Cache(getApplication().getCacheDir(), cacheSize);


        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        GsonConverterFactory converterFactory = GsonConverterFactory.create(gson);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(converterFactory)
                .client(client)
                .build();
    }
}
