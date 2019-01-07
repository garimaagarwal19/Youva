package com.decurtis.youva.di.module;

import com.decurtis.youva.GoogleSignInModule;
import com.decurtis.youva.di.scope.FragmentScope;
import com.decurtis.youva.model.UserDetails;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Garima Chamaria on 04/01/19.
 */

@Module
public class LoginFragmentModule {

    @Provides
    @FragmentScope
    GoogleSignInModule provideGoogleSignInModule() {
        return new GoogleSignInModule();
    }
}
