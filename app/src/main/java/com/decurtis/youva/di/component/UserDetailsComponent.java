package com.decurtis.youva.di.component;

import com.decurtis.youva.di.module.UserDetailsModule;
import com.decurtis.youva.di.scope.FragmentScope;
import com.decurtis.youva.fragment.UserDetailsFragment;
import com.decurtis.youva.model.UserDetails;

import dagger.Subcomponent;

/**
 * Created by Garima Chamaria on 04/01/19.
 */

@FragmentScope
@Subcomponent(modules = UserDetailsModule.class)
public interface UserDetailsComponent {
    void inject(UserDetailsFragment userDetailsFragment);

    UserDetails getUserDetails();
}
