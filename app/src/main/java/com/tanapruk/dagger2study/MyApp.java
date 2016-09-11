package com.tanapruk.dagger2study;

import android.app.Application;

/**
 * Created by trusttanapruk on 9/11/2016.
 */
public class MyApp extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https//api.github.com"))
                .build();

    }

    public NetComponent getmNetComponent() {
        return mNetComponent;
    }
}
