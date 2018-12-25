package com.decurtis.youva;

import android.app.Application;

/**
 * Created by Garima Chamaria on 25/12/18.
 */
public class MainApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ServiceFactory.create(getApplicationContext());
    }
}
