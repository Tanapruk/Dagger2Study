package com.tanapruk.dagger2study;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by trusttanapruk on 9/11/2016.
 */

/**
 * Component is a bridge that link modules together
 * This component links AppModule together with UtilModule
 */
@Singleton
@Component(modules = {AppModule.class, UtilModule.class})
public interface UtilComponent {
    void inject(MainActivity activity);

}
