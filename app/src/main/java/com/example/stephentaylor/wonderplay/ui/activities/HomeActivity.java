package com.example.stephentaylor.wonderplay.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.stephentaylor.wonderplay.BaseApplication;
import com.example.stephentaylor.wonderplay.di.components.ActivityComponent;
import com.example.stephentaylor.wonderplay.mvp.presenters.HomePresenter;
import com.example.stephentaylor.wonderplay.mvp.views.HomeView;

import butterknife.ButterKnife;

/**
 * Created by Ste on 12/03/2017.
 */

public class HomeActivity extends BaseActivity<HomeView, HomePresenter> implements HomeView {


    @Override
    public HomePresenter createPresenter() {
        HomePresenter p = new HomePresenter();
        ((BaseApplication)getApplication()).getAppComponent().inject(p);
        return p;
    }

    @Override
    protected void injectDependencies(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void showDetails() {

    }
}
