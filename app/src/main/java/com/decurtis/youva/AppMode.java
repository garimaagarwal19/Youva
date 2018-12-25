package com.decurtis.youva;

/**
 * Created by Garima Chamaria on 25/12/18.
 */
public enum AppMode {
    DEFAULT(0),
    BUSINESS(1),
    INDIVIDUAL(2);

    private int mValue;

    AppMode(int value){
        mValue = value;
    }

    public int getValue(){
        return mValue;
    }
}
