package com.example.receptionmanager;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MainLifecycle implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void init() {

        DataFromDB.getDataFromDB();
    }
}
