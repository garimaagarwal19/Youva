package com.decurtis.youva.di.module;

import android.app.Activity;
import android.content.Context;

import com.decurtis.youva.di.scope.ActivityScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Garima Chamaria on 02/01/19.
 */

@Module
public class ActivityModule {
    private static Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Named("activity_context")
    @Provides
    @ActivityScope
    public Context provideContext() {
        return mActivity;
    }
}
