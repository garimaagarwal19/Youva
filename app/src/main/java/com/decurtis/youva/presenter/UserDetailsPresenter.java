package com.decurtis.youva.presenter;

import com.decurtis.youva.BasePresenter;
import com.decurtis.youva.DataEventListener;
import com.decurtis.youva.DatabaseServiceManager;
import com.decurtis.youva.SharedPreferenceManager;
import com.decurtis.youva.model.UserDetails;

/**
 * Created by Garima Chamaria on 09/01/19.
 */
public class UserDetailsPresenter extends BasePresenter {

    private SharedPreferenceManager mSharedPreferenceManager;
    private DatabaseServiceManager mDatabaseServiceManager;

    public UserDetailsPresenter(SharedPreferenceManager sharedPreferenceManager, DatabaseServiceManager databaseServiceManager) {
        mSharedPreferenceManager = sharedPreferenceManager;
        mDatabaseServiceManager = databaseServiceManager;
    }

    public int getAppMode() {
        return mSharedPreferenceManager.getAppMode();
    }

    public UserDetails getLoggedInAccount() {
        return mSharedPreferenceManager.getLoggedInAccount();
    }

    public void setIsProfileCreated(boolean value) {
        mSharedPreferenceManager.setIsProfileCreated(value);
    }

    public void saveDataToDatabase(final UserDetails details) {
        mDatabaseServiceManager.getLoggedInAccount(getLoggedInAccount().getKey(), new DataEventListener<UserDetails>() {
            @Override
            public void OnSuccess(UserDetails userDetails) {
                userDetails.setFirstname(details.getFirstname());
                userDetails.setLastname(details.getLastname());
                userDetails.setPhonenumber(details.getPhonenumber());
                userDetails.setGender(details.getGender());

                userDetails.setAddress(details.getAddress());
                userDetails.setIndividuallonglat(details.getIndividuallonglat());
                userDetails.setIndividualInterest(details.getIndividualInterest());

                userDetails.setBusinessname(details.getBusinessname());
                userDetails.setBusinessaddress(details.getBusinessaddress());
                userDetails.setBusinesslonglat(details.getBusinesslonglat());

                mDatabaseServiceManager.saveUserBasicData(userDetails);
            }
        });
    }
}
