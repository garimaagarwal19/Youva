package com.decurtis.youva.di.module;

import com.decurtis.youva.DatabaseServiceManager;
import com.decurtis.youva.DatabaseServiceManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Garima Chamaria on 03/01/19.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    DatabaseServiceManager provideDataBaseService() {
        return new DatabaseServiceManagerImpl();
    }
}
