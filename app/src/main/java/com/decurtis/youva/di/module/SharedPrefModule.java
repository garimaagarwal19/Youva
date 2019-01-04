package com.decurtis.youva.di.module;

import android.content.Context;

import com.decurtis.youva.SharedPrefManagerImpl;
import com.decurtis.youva.SharedPreferenceManager;
import com.google.gson.Gson;

import javax.inject.Named;
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
    SharedPreferenceManager provideSharedPreferenceManager(@Named("application_context") Context context, Gson gson) {
        return SharedPrefManagerImpl.getInstance(context, gson);
    }
}
