package com.decurtis.youva.di.component;

import android.app.Activity;

import com.decurtis.youva.MainActivity;
import com.decurtis.youva.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by Garima Chamaria on 02/01/19.
 */

@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void injectActivity(MainActivity mainActivity);

}
