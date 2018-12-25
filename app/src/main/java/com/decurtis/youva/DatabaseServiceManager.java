package com.decurtis.youva;

import com.decurtis.youva.model.UserDetails;

public interface DatabaseServiceManager {

    void saveUserBasicData(UserDetails userDetails);

    UserDetails getLoggedInAccount(String accountId);
}
