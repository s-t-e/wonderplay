package com.example.stephentaylor.wonderplay.di.modules;

import com.example.stephentaylor.wonderplay.BaseApplication;
import com.example.stephentaylor.wonderplay.mvp.PresenterCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ste on 12/03/2017.
 */

@Module
public class AppModule {

    private static final String TAG = AppModule.class.getSimpleName();

    BaseApplication mApplication;

    public AppModule(BaseApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    PresenterCache providesPresenterCache() {
        return new PresenterCache();
    }
}
