package com.decurtis.youva;

import android.support.annotation.NonNull;
import android.util.Log;

import com.decurtis.youva.executor.ThreadExecutor;
import com.decurtis.youva.model.UserDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseServiceManagerImpl implements DatabaseServiceManager {

    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mFireBaseDatabaseUserReference;

    private ThreadExecutor mThreadExecutor;

    public DatabaseServiceManagerImpl(ThreadExecutor threadExecutor, FirebaseDatabase firebaseDatabase) {
        mThreadExecutor = threadExecutor;
        createDatabase(firebaseDatabase);
        createDatabaseUserReference();
    }

    private void createDatabase(FirebaseDatabase firebaseDatabase) {
        mFireBaseDatabase = firebaseDatabase;
    }

    private void createDatabaseUserReference() {
        mFireBaseDatabaseUserReference = mFireBaseDatabase.getReference(AppConstants.USERS);
    }

    @Override
    public void saveUserBasicData(final UserDetails userDetails) {
        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DatabaseReference databaseReference = mFireBaseDatabaseUserReference.child(userDetails.getKey());
                Task<Void> task = databaseReference.setValue(userDetails);

                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("saveUserBasicData", e.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public void getLoggedInAccount(final String accountId, final DataEventListener<UserDetails> eventListener) {
        final DatabaseReference ref = mFireBaseDatabaseUserReference.child(accountId);

        mThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
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
