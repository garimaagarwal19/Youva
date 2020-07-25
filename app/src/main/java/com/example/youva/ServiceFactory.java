package com.example.youva;

import com.example.youva.executor.ThreadExecutor;

public class ServiceFactory {

    public ServiceFactory() {
        /*Empty constructor*/
    }

    public static DatabaseServiceManager getDatabaseManager() {
        return new DatabaseServiceManagerImpl();
    }

    public static SharedPreferenceManager getSharedPreferencesManager() {
        return SharedPrefManagerImpl.getInstance();
    }

    public static ThreadExecutor getThreadExecutor() {
        return ThreadExecutor.getInstance();
    }
}
