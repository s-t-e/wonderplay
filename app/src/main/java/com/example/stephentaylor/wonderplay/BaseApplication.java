package com.example.stephentaylor.wonderplay;

import android.app.Application;

import com.example.stephentaylor.wonderplay.di.components.AppComponent;
import com.example.stephentaylor.wonderplay.di.components.DaggerAppComponent;

/**
 * Created by Ste on 13/02/2017.
 */

public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new com.example.stephentaylor.wonderplay.di.modules.AppModule(this))
                .build();

        appComponent.inject(this);

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
