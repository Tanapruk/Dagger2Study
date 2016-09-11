package com.tanapruk.dagger2study;

import android.app.Application;

/**
 * Created by trusttanapruk on 9/11/2016.
 */
public class MainApplication extends Application {
    private UtilComponent mUtilComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Instantiating the component
        //DaggerUtilComponent will show up after build
        mUtilComponent = DaggerUtilComponent.builder()
                .appModule(new AppModule(this))
                .utilModule(new UtilModule())
                .build();

    }

    public UtilComponent getUtilComponent() {
        return mUtilComponent;
    }
}
