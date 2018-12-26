package com.decurtis.youva;

import android.app.Application;
import android.content.Context;

/**
 * Created by Garima Chamaria on 25/12/18.
 */
public class MainApplication extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        ServiceFactory.create(getApplicationContext());
    }

    public static Context getAppContext() {
        return mContext;
    }
}
