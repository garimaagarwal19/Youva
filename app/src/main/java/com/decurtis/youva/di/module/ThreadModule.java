package com.decurtis.youva.di.module;

import com.decurtis.youva.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Garima Chamaria on 03/01/19.
 */

@Module
public class ThreadModule {

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor() {
        return ThreadExecutor.getInstance();
    }
}
