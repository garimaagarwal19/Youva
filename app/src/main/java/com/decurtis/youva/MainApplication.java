package com.decurtis.youva;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.decurtis.youva.di.component.ActivityComponent;
import com.decurtis.youva.di.component.ApplicationComponent;
import com.decurtis.youva.di.component.DaggerApplicationComponent;
import com.decurtis.youva.di.component.LoginComponent;
import com.decurtis.youva.di.component.ModeSelectionComponent;
import com.decurtis.youva.di.component.UserDetailsComponent;
import com.decurtis.youva.di.module.ActivityModule;
import com.decurtis.youva.di.module.ApplicationModule;
import com.decurtis.youva.di.module.DataModule;
import com.decurtis.youva.di.module.LoginFragmentModule;
import com.decurtis.youva.di.module.ModeSelectionModule;
import com.decurtis.youva.di.module.SharedPrefModule;
import com.decurtis.youva.di.module.ThreadModule;
import com.decurtis.youva.di.module.UserDetailsModule;
import com.google.gson.Gson;


/**
 * Created by Garima Chamaria on 25/12/18.
 */
public class MainApplication extends Application{
    private static MainApplication mAppInstance;
    private static Gson gson;

    //Dagger
    private static ApplicationComponent mApplicationComponent;
    private ActivityComponent mActivityComponent;
    private LoginComponent mLoginComponent;
    private ModeSelectionComponent mModeSelectionComponent;
    private UserDetailsComponent mUserDetailsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new Gson();
        mAppInstance = this;

        mApplicationComponent = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this)).
                dataModule(new DataModule()).
                threadModule(new ThreadModule()).
                sharedPrefModule(new SharedPrefModule()).
                build();

        mApplicationComponent.injectApplication(this);
    }

    public static Context getContext(){
        return mAppInstance;
    }



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

    public static MainApplication getInstance(){
        return mAppInstance;
    }

    public static ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public ActivityComponent plusActivityComponent(Activity activity) {
        if(mActivityComponent == null) {
            mActivityComponent = mApplicationComponent.plusActivityComponent(new ActivityModule(activity));
        }
        return mActivityComponent;
    }

    public LoginComponent plusLoginComponent() {
        if(mLoginComponent == null) {
            mLoginComponent = mActivityComponent.plusLoginComponent(new LoginFragmentModule());
        }
        return mLoginComponent;
    }

    public ModeSelectionComponent plusModeSelectionComponent() {
        if(mModeSelectionComponent == null) {
            mModeSelectionComponent = mActivityComponent.plusModeSelectionComponent(new ModeSelectionModule());
        }
        return mModeSelectionComponent;
    }

    public UserDetailsComponent plusUserDetailsComponent() {
        if(mUserDetailsComponent == null) {
            mUserDetailsComponent = mActivityComponent.plusUserDetailsComponent(new UserDetailsModule());
        }
        return mUserDetailsComponent;
    }

    public void clearActivityComponent(){
        mActivityComponent = null;
    }

    public void clearLoginComponent() {
        mLoginComponent = null;
    }

    public void clearModeSelectionComponent() {
        mLoginComponent = null;
    }

    public void clearUserDetailsComponent() {
        mUserDetailsComponent = null;
    }
}
