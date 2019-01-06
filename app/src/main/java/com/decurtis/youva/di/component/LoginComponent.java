package com.decurtis.youva.di.component;

import com.decurtis.youva.di.module.LoginFragmentModule;
import com.decurtis.youva.di.scope.FragmentScope;
import com.decurtis.youva.fragment.LoginFragment;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by Garima Chamaria on 04/01/19.
 */

@Subcomponent(modules = LoginFragmentModule.class)
@FragmentScope
public interface LoginComponent {
    void inject(LoginFragment loginFragment);
}
