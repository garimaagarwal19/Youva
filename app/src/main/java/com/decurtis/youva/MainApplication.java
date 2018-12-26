package com.decurtis.youva;

import android.app.Application;
import android.content.Context;

/**
 * Created by Garima Chamaria on 25/12/18.
 */
public class MainApplication extends Application{
    private static MainApplication applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceFactory.create(getApplicationContext());
        applicationContext = this;
    }

    public static Context getContext(){
        return applicationContext;
    }
}
