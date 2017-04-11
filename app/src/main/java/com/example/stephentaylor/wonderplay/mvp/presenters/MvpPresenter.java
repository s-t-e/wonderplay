package com.example.stephentaylor.wonderplay.mvp.presenters;

import com.example.stephentaylor.wonderplay.mvp.views.MvpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ste on 12/03/2017.
 */

public abstract class MvpPresenter<V extends MvpView> {

    private V view;

    private List<OnViewAttachedAction<V>> pendingActions = new ArrayList<>();

    public void viewAttached(V view) {
        this.view = view;
        for (OnViewAttachedAction<V> action : pendingActions) {
            action.execute(view);
        }
        pendingActions.clear();
    }

    public void viewDetached() {
        this.view = null;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    protected V getView() {
        return view;
    }

    /**
     * called when a new view is created.
     * Put here the first time initialisation needed by a new view.
     * @param view
     */
    public abstract void viewCreated(V view);

    protected interface OnViewAttachedAction<T> {
        void execute(T view);
    }

    /**
     * if the view is attached, the action is executed as per the execute
     * implementation.
     * otherwise, the action is added to the list.
     * @param action
     */
    protected void whenViewAttached(OnViewAttachedAction<V> action) {
        if (isViewAttached()) {
            action.execute(getView());
        } else {
            pendingActions.add(action);
        }
    }
}
