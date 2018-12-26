package com.decurtis.youva;

import com.decurtis.youva.model.UserDetails;

public interface DatabaseServiceManager {

    void saveUserBasicData(UserDetails userDetails);

    void getLoggedInAccount(String accountId, DataEventListener<UserDetails> eventListener);
}
