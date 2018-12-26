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
    public void saveUserBasicData(final UserDetails userDetails) {
        ServiceFactory.getThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mFireBaseDatabaseUserReference.child(userDetails.getKey()).setValue(userDetails);
            }
        });
    }

    @Override
    public void getLoggedInAccount(String accountId, final DataEventListener<UserDetails> eventListener) {
        final DatabaseReference ref = mFireBaseDatabaseUserReference.child(accountId);

        ServiceFactory.getThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                        if(eventListener != null)
                            eventListener.OnSuccess(userDetails);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
