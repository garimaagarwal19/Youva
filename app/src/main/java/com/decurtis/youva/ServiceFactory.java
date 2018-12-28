package com.decurtis.youva;

import android.content.Context;

import com.decurtis.youva.executor.ThreadExecutor;

public class ServiceFactory {
    private static Context mContext;

    public ServiceFactory(Context context) {
        mContext = context;
    }

    public static void create(Context context) {
        mContext = context;
    }

    public static DatabaseServiceManager getDatabaseManager(){
        return new DatabaseServiceManagerImpl();
    }

    public static SharedPrefManager getSharedPreferencesManager() {
        return SharedPrefManager.getInstance(mContext);
    }

    public static ThreadExecutor getThreadExecutor() {
        return ThreadExecutor.getInstance();
    }
}
