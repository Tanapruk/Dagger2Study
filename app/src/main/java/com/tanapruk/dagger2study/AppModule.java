package com.tanapruk.dagger2study;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by trusttanapruk on 9/11/2016.
 */

/**
 * Annotate with @Module to tell every method that look for injection to come and look inside me
 * whether this @Provides what it looks for.
 * In this module it provides {@link Application}
 * so every other method that need {@link Application} will satisfy its injection from here.
 */
@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }


    /**
     * There are other methods that need {@link Application} so we annotate with @Provides
     * so those other methods will automatically consume from this provide method
     * Annotate @Singleton is self explanatory
     * @return
     */
    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}
