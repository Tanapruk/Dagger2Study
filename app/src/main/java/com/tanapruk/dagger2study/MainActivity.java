package com.tanapruk.dagger2study;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    //make this share pref get all providers
    @Inject
    SharedPreferences sharedPreferencesByInjection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MainApplication) getApplication()).getUtilComponent().inject(this);


        //use it without instantiating by
        sharedPreferencesByInjection.edit().putString("Test writing string", "This is my string").apply();

    }

}
