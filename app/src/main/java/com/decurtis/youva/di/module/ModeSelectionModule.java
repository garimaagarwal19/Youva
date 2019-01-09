package com.decurtis.youva.di.module;

import com.decurtis.youva.SharedPreferenceManager;
import com.decurtis.youva.di.scope.FragmentScope;
import com.decurtis.youva.presenter.ModeSelectionPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Garima Chamaria on 04/01/19.
 */

@Module
public class ModeSelectionModule {

    @FragmentScope
    @Provides
    ModeSelectionPresenter provideModeSelectionPresenter(SharedPreferenceManager sharedPreferenceManager) {
        return new ModeSelectionPresenter(sharedPreferenceManager);
    }
}
