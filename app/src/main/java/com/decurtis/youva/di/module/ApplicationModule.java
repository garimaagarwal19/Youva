package com.decurtis.youva.di.module;

import android.content.Context;
import com.google.gson.Gson;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Garima Chamaria on 02/01/19.
 */

@Module
public class ApplicationModule {
    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }
}
