package com.decurtis.youva;

public class ServiceFactory {

    public static DatabaseServiceManager getDatabaseManager(){
        return new DatabaseServiceManagerImpl();
    }

}
