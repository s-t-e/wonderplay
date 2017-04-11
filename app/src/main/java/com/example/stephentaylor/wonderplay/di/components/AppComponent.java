package com.example.stephentaylor.wonderplay.di.components;


import com.example.stephentaylor.wonderplay.BaseApplication;
import com.example.stephentaylor.wonderplay.di.modules.ActivityModule;
import com.example.stephentaylor.wonderplay.di.modules.AppModule;
import com.example.stephentaylor.wonderplay.mvp.presenters.HomePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ste on 12/03/2017.
 */

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {

    //activity subcomponent
    ActivityComponent plus(ActivityModule module);

    //presenters (we keep them out of the activity scope because they survive the activity/fragment destruction)
    void inject(HomePresenter p);

    //other
    void inject(BaseApplication baseApplication);


}