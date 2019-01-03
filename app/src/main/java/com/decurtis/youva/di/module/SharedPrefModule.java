package com.decurtis.youva.di.module;

import com.decurtis.youva.SharedPrefManagerImpl;
import com.decurtis.youva.SharedPreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Garima Chamaria on 03/01/19.
 */

@Module
public class SharedPrefModule {

    @Provides
    @Singleton
    SharedPreferenceManager provideSharedPreferenceManager() {
        return SharedPrefManagerImpl.getInstance();
    }
}
