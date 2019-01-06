package com.decurtis.youva.di.component;

import com.decurtis.youva.di.module.ModeSelectionModule;
import com.decurtis.youva.di.scope.FragmentScope;
import com.decurtis.youva.fragment.ModeSelectionFragment;

import dagger.Subcomponent;

/**
 * Created by Garima Chamaria on 04/01/19.
 */

@FragmentScope
@Subcomponent(modules = ModeSelectionModule.class)
public interface ModeSelectionComponent {
    void inject(ModeSelectionFragment modeSelectionFragment);
}
