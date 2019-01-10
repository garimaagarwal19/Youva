package com.decurtis.youva.di.module;

import com.decurtis.youva.DatabaseServiceManager;
import com.decurtis.youva.SharedPreferenceManager;
import com.decurtis.youva.di.scope.FragmentScope;
import com.decurtis.youva.model.UserDetails;
import com.decurtis.youva.presenter.UserDetailsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Garima Chamaria on 04/01/19.
 */

@Module
public class UserDetailsModule {

    @Provides
    UserDetails provideUserDetails() {
        return new UserDetails();
    }

    @Provides
    @FragmentScope
    UserDetailsPresenter provideUserDetailsPresenter(SharedPreferenceManager sharedPreferenceManager, DatabaseServiceManager databaseServiceManager) {
        return new UserDetailsPresenter(sharedPreferenceManager, databaseServiceManager);
    }
}
