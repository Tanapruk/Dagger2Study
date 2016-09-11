package com.tanapruk.dagger2study;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by trusttanapruk on 9/11/2016.
 */
@Module
public class UtilModule {
    /**
     *
     * @param application is @provides by {@code AppModule#providesApplication}
     * @return
     */
    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

}
