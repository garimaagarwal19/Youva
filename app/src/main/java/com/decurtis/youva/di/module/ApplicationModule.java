package com.decurtis.youva.di.module;

import android.app.Application;
import android.content.Context;

import com.decurtis.youva.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Garima Chamaria on 02/01/19.
 */

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return mApplication;
    }
}
