package com.example.youva;

import com.example.youva.model.UserDetails;

public interface SharedPreferenceManager {

    void setAppMode(int appMode);

    int getAppMode();

    void setIsProfileCreated(boolean isProfileCreated);

    boolean getIsProfileCreated();

    void setLoggedInAccount(UserDetails userDetails);

    UserDetails getLoggedInAccount();

    void invalidate();

}
