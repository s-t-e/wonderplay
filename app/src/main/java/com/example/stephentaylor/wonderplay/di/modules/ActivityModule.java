package com.example.stephentaylor.wonderplay.di.modules;

import com.example.stephentaylor.wonderplay.di.ActivityScope;
import com.example.stephentaylor.wonderplay.ui.activities.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ste on 12/03/2017.
 */

@Module
public class ActivityModule {

    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    BaseActivity provideActivity() {
        return activity;
    }
}
