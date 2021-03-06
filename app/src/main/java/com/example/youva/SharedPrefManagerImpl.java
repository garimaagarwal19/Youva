package com.example.youva;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.youva.model.AppMode;
import com.example.youva.model.UserDetails;

/**
 * Created by Garima Chamaria on 25/12/18.
 */
public class SharedPrefManagerImpl implements SharedPreferenceManager {
    private static final String SHARED_PREFERENCES_NAME = "youva";
    private static final String APP_MODE = "AppMode";
    private static final String PROFILE_CREATED = "Profile_created";
    private static final String LOGGED_IN_ACCOUNT = "Account";

    private static SharedPreferences mSharedPreference;
    private static SharedPreferenceManager mInstance;

    private SharedPrefManagerImpl(Context context) {
        mSharedPreference = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferenceManager getInstance() {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerImpl(MainApplication.getContext());
        }
        return mInstance;
    }

    @Override
    public void setAppMode(int value) {
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putInt(APP_MODE, value);
        editor.commit();
    }

    @Override
    public int getAppMode() {
        return mSharedPreference.getInt(APP_MODE, AppMode.DEFAULT.getValue());
    }

    @Override
    public void setIsProfileCreated(boolean isProfileCreated) {
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putBoolean(PROFILE_CREATED, isProfileCreated);
        editor.commit();
    }

    @Override
    public boolean getIsProfileCreated() {
        return mSharedPreference.getBoolean(PROFILE_CREATED, false);
    }

    @Override
    public void setLoggedInAccount(UserDetails userDetails) {
        String json = MainApplication.getGsonObject().toJson(userDetails);
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(LOGGED_IN_ACCOUNT, json);
        editor.commit();
    }

    @Override
    public UserDetails getLoggedInAccount() {
        String json = mSharedPreference.getString(LOGGED_IN_ACCOUNT, null);
        return MainApplication.getGsonObject().fromJson(json, UserDetails.class);
    }

    @Override
    public void invalidate() {
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.remove(LOGGED_IN_ACCOUNT);
        editor.putInt(APP_MODE, AppMode.DEFAULT.getValue());
        editor.putBoolean(PROFILE_CREATED, false);
        editor.commit();
    }
}
