package com.decurtis.youva.di.component;

import com.decurtis.youva.MainActivity;
import com.decurtis.youva.di.module.ActivityModule;
import com.decurtis.youva.di.module.LoginFragmentModule;
import com.decurtis.youva.di.module.ModeSelectionModule;
import com.decurtis.youva.di.module.UserDetailsModule;
import com.decurtis.youva.di.scope.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Garima Chamaria on 02/01/19.
 */

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void injectActivity(MainActivity mainActivity);

    LoginComponent plusLoginComponent(LoginFragmentModule loginFragmentModule);

    ModeSelectionComponent plusModeSelectionComponent(ModeSelectionModule modeSelectionModule);

    UserDetailsComponent plusUserDetailsComponent(UserDetailsModule userDetailsModule);
}
