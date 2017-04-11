package com.example.stephentaylor.wonderplay.di.components;

import com.example.stephentaylor.wonderplay.di.ActivityScope;
import com.example.stephentaylor.wonderplay.di.modules.ActivityModule;
import com.example.stephentaylor.wonderplay.ui.activities.HomeActivity;

import dagger.Subcomponent;

/**
 * Created by Ste on 12/03/2017.
 */

@ActivityScope
@Subcomponent(
        modules = {
                ActivityModule.class
        }
)

public interface ActivityComponent {

    //activities
    void inject(HomeActivity activity);

    //fragments

}
