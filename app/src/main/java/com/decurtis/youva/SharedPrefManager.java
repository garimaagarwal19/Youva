package com.decurtis.youva;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Garima Chamaria on 25/12/18.
 */
public class SharedPrefManager {
    private static final String SHARED_PREFERENCES_NAME = "youva";
    private static final String APP_MODE = "App_Mode";

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
}
