package com.example.stephentaylor.wonderplay.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.stephentaylor.wonderplay.BaseApplication;
import com.example.stephentaylor.wonderplay.di.components.ActivityComponent;
import com.example.stephentaylor.wonderplay.di.modules.ActivityModule;
import com.example.stephentaylor.wonderplay.mvp.PresenterCache;
import com.example.stephentaylor.wonderplay.mvp.presenters.MvpPresenter;
import com.example.stephentaylor.wonderplay.mvp.views.MvpView;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Ste on 12/03/2017.
 */

public abstract class BaseActivity <V extends MvpView, P extends MvpPresenter<V>> extends AppCompatActivity implements MvpView {

    @Inject
    protected PresenterCache presenterCache;

    protected P presenter;

    public abstract P createPresenter();

    private static final String PRESENTER_CAHCE_ID_BUNDLE_ARG = "presenter_cache_id_bundle_arg";
    private static final String TAG = BaseActivity.class.getSimpleName();

    protected ActivityComponent activityComponent;

    private boolean newActivity;

    /**
     * Subclasses must implement this method by callign "inject"
     * on the provided ActivityComponent and passing themselves as parameter.
     * @param component
     */
    protected abstract void injectDependencies(ActivityComponent component);

    public boolean shouldRetainPresenter() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        activityComponent = ((BaseApplication)getApplication()).getAppComponent().plus(new ActivityModule(this));
        super.onCreate(savedInstanceState);
        injectDependencies(activityComponent);
        MvpPresenter fromCache = null;
        if (shouldRetainPresenter() && savedInstanceState != null
                && savedInstanceState.containsKey(PRESENTER_CAHCE_ID_BUNDLE_ARG)) {
            String id = savedInstanceState.getString(PRESENTER_CAHCE_ID_BUNDLE_ARG);
            fromCache = presenterCache.retrievePresenter(id);
            presenterCache.removeFromCache(id);
        }
        presenter = (fromCache != null) ? (P)fromCache : createPresenter();
        newActivity = true;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (newActivity) {
            presenter.viewCreated((V)this);
            newActivity = false;
        }
        presenter.viewAttached((V)this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (shouldRetainPresenter() && !isFinishing()) {
            String id = presenterCache.save(presenter);
            outState.putString(PRESENTER_CAHCE_ID_BUNDLE_ARG, id);
        }
    }

    @Override
    protected void onStop() {
        presenter.viewDetached();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        activityComponent = null;
        super.onDestroy();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}

