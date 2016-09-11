package com.tanapruk.dagger2study;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by trusttanapruk on 9/11/2016.
 */


@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}
