package com.decurtis.youva.di.component;

import com.decurtis.youva.MainApplication;
import com.decurtis.youva.di.module.ActivityModule;
import com.decurtis.youva.di.module.ApplicationModule;
import com.decurtis.youva.di.module.DataModule;
import com.decurtis.youva.di.module.SharedPrefModule;
import com.decurtis.youva.di.module.ThreadModule;

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

    ActivityComponent plusActivityComponent(ActivityModule activityModule);
}
