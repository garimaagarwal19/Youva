package com.decurtis.youva;

import android.app.Application;
import android.content.Context;

import com.decurtis.youva.di.component.ApplicationComponent;
import com.decurtis.youva.di.component.DaggerApplicationComponent;
import com.decurtis.youva.di.module.ApplicationModule;
import com.decurtis.youva.di.module.DataModule;
import com.decurtis.youva.di.module.SharedPrefModule;
import com.decurtis.youva.di.module.ThreadModule;
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

        mApplicationComponent = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this)).
                dataModule(new DataModule()).
                threadModule(new ThreadModule()).
                sharedPrefModule(new SharedPrefModule()).
                build();

        mApplicationComponent.injectApplication(this);
    }

    public static Context getContext(){
        return applicationContext;
    }

    public static Gson getGsonObject(){
        return gson;
    }

    private static ApplicationComponent mApplicationComponent;

   /* @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this)).
                dataModule(new DataModule()).
                threadModule(new ThreadModule()).
                sharedPrefModule(new SharedPrefModule()).
                build();

        mApplicationComponent.injectApplication(this);
    }*/

    public static ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }


}
