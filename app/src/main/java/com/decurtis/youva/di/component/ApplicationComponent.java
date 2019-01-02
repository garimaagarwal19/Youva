package com.decurtis.youva.di.component;

import android.content.Context;

import com.decurtis.youva.MainApplication;
import com.decurtis.youva.di.module.ApplicationModule;

import dagger.Component;

/**
 * Created by Garima Chamaria on 02/01/19.
 */

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void injectApplication(MainApplication mainApplication);

    Context getContext();
}
