package com.decurtis.youva;

import android.support.annotation.NonNull;

import com.decurtis.youva.model.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    public void saveUserBasicData(UserDetails userDetails) {
        mFireBaseDatabaseUserReference.child(userDetails.getKey()).setValue(userDetails);
    }

    public UserDetails getLoggedInAccount(String accountId) {
        DatabaseReference ref = mFireBaseDatabaseUserReference.child(accountId);
        final UserDetails userDetails = new UserDetails();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserDetails details = dataSnapshot.getValue(UserDetails.class);
                userDetails.setEmail(details.getEmail());
                userDetails.setImageURL(details.getImageURL());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return userDetails;
    }

}
