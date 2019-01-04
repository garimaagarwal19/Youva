package com.decurtis.youva.di.component;

import android.content.Context;

import com.decurtis.youva.DatabaseServiceManager;
import com.decurtis.youva.MainActivity;
import com.decurtis.youva.MainApplication;
import com.decurtis.youva.SharedPreferenceManager;
import com.decurtis.youva.di.module.ApplicationModule;
import com.decurtis.youva.di.module.DataModule;
import com.decurtis.youva.di.module.SharedPrefModule;
import com.decurtis.youva.di.module.ThreadModule;
import com.decurtis.youva.executor.ThreadExecutor;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Garima Chamaria on 02/01/19.
 */

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class, SharedPrefModule.class, ThreadModule.class})
/*
Dependencies provided by each declared module in this component is available for each other
*/

public interface ApplicationComponent {

    void injectApplication(MainApplication mainApplication);

    @Named("application_context")
    Context getContext();

    Gson getGson();

    DatabaseServiceManager getDataBaseService();

    SharedPreferenceManager getSharedPreferenceManager();

    ThreadExecutor getThreadExecutor();
}
