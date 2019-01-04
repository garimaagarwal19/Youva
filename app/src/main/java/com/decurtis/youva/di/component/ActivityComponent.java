package com.decurtis.youva.di.component;

import android.content.Context;

import com.decurtis.youva.DatabaseServiceManager;
import com.decurtis.youva.MainActivity;
import com.decurtis.youva.SharedPreferenceManager;
import com.decurtis.youva.di.module.ActivityModule;
import com.decurtis.youva.di.scope.ActivityScope;
import com.decurtis.youva.executor.ThreadExecutor;
import com.google.gson.Gson;

import javax.inject.Named;

import dagger.Component;

/**
 * Created by Garima Chamaria on 02/01/19.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void injectActivity(MainActivity mainActivity);

   /* @Named("activity_context")
    Context getActivityContext();

    @Named("application_context")
    Context getContext();

    Gson getGson();

    DatabaseServiceManager getDataBaseService();

    SharedPreferenceManager getSharedPreferenceManager();

    ThreadExecutor getThreadExecutor();*/

}
