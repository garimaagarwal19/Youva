package com.example.youva;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by Garima Chamaria on 25/12/18.
 */
public class MainApplication extends Application{
    private static MainApplication applicationContext;
    private static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new Gson();
        applicationContext = this;
    }

    public static Context getContext(){
        return applicationContext;
    }

    public static Gson getGsonObject(){
        return gson;
    }
}
