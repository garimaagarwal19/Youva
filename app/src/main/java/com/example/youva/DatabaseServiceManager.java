package com.example.youva;

import com.example.youva.model.UserDetails;

public interface DatabaseServiceManager {

    void saveUserBasicData(UserDetails userDetails);

    void getLoggedInAccount(String accountId, DataEventListener<UserDetails> eventListener);
}
