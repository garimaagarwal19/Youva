package com.decurtis.youva.presenter;

import com.decurtis.youva.BasePresenter;
import com.decurtis.youva.DatabaseServiceManager;
import com.decurtis.youva.SharedPreferenceManager;
import com.decurtis.youva.model.UserDetails;


/**
 * Created by Garima Chamaria on 09/01/19.
 */
public class LoginPresenter extends BasePresenter {

    private DatabaseServiceManager mDatabaseServiceManager;
    private SharedPreferenceManager mSharedPreferenceManager;

    public LoginPresenter(DatabaseServiceManager databaseServiceManager, SharedPreferenceManager sharedPreferenceManager) {
        mDatabaseServiceManager = databaseServiceManager;
        mSharedPreferenceManager = sharedPreferenceManager;
    }

    public void saveDataToDatabase(String fullName, String email, String photoUrl, String id) {
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(email);
        if (photoUrl != null && photoUrl.length() > 0)
            userDetails.setImageURL(photoUrl);
        userDetails.setName(fullName);
        userDetails.setKey(id);

        mDatabaseServiceManager.saveUserBasicData(userDetails);
        mSharedPreferenceManager.setLoggedInAccount(userDetails);
    }
}
