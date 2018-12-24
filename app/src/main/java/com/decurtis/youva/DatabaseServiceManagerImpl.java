package com.decurtis.youva;

import com.decurtis.youva.model.UserDeails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DatabaseServiceManagerImpl implements DatabaseServiceManager {

    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mFireBaseDatabaseUserReference;

    public DatabaseServiceManagerImpl() {
        createDatabase();
        createDatabaseUserReference();
    }

    private void createDatabase() {
        mFireBaseDatabase = FirebaseDatabase.getInstance();
    }

    private void createDatabaseUserReference() {
        mFireBaseDatabaseUserReference = mFireBaseDatabase.getReference(AppConstants.USERS);
    }

    @Override
    public void saveUserBasicData(UserDeails userDeails) {
        mFireBaseDatabaseUserReference.child(userDeails.getKey()).setValue(userDeails);
    }
}
