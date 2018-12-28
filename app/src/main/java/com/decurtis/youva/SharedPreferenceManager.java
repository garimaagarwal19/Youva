package com.decurtis.youva;

import com.decurtis.youva.model.UserDetails;

public interface SharedPreferenceManager {

    void setAppMode(int appMode);

    int getAppMode();

    void setLoggedInAccount(UserDetails userDetails);

    UserDetails getLoggedInAccount();

    void invalidate();

}
