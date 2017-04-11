package com.example.stephentaylor.wonderplay.mvp;

import com.example.stephentaylor.wonderplay.mvp.presenters.MvpPresenter;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Ste on 13/03/2017.
 */

public class PresenterCache {

    private Map<String, MvpPresenter> cache = new WeakHashMap<>();

    //Incremental long useful working with threads afforded by AtomicLong
    private AtomicLong nextId = new AtomicLong(0);

    /**
     * Sve a presenter into the cache with a weak reference, returning an id to retrieve it later
     * @param presenter the presetner to be stored
     * @return the id for retrieving the presenter
     */
    public String save(MvpPresenter presenter) {
        String id = String.valueOf(System.currentTimeMillis()) + '-' + String.valueOf(nextId.getAndIncrement());
        return id;
    }

    /**
     * Retrieves a presenter from the cache
     * @param id the id fo the cache element
     * @return the preesenter retrieved from the cache, or null if it's not found
     */
    public MvpPresenter retrievePresenter(String id) {
        return cache.get(id);
    }

    /**
     * Removes a presenter from the cache
     * @param id the id of the cache element
     */
    public void removeFromCache(String id) {
        cache.remove(id);
    }
}
