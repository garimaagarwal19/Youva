package com.decurtis.youva;

import android.content.Context;
import android.content.SharedPreferences;

import com.decurtis.youva.model.AppMode;
import com.decurtis.youva.model.UserDetails;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Garima Chamaria on 25/12/18.
 */
public class SharedPrefManager {
    private static final String SHARED_PREFERENCES_NAME = "youva";
    private static final String APP_MODE = "AppMode";
    private static final String LOGGED_IN_ACCOUNT = "Account";

    private static SharedPreferences mSharedPreference;
    private static SharedPrefManager mInstance;
    private Context mContext;

    private SharedPrefManager(Context context) {
        mContext = context;
        mSharedPreference = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPrefManager getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void setAppMode(int value) {
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putInt(APP_MODE, value);
        editor.commit();
    }

    public int getAppMode() {
        return mSharedPreference.getInt(APP_MODE, AppMode.DEFAULT.getValue());
    }

    public void setLoggedInAccount(UserDetails userDetails) {
        Gson gson = new Gson();
        String json = gson.toJson(userDetails);

        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(LOGGED_IN_ACCOUNT, json);
        editor.commit();
    }

    public UserDetails getLoggedInAccount() {
        String json = mSharedPreference.getString(LOGGED_IN_ACCOUNT, null);

        Gson gson = new Gson();
        UserDetails userDetails = gson.fromJson(json, UserDetails.class);
        return userDetails;
    }
}
