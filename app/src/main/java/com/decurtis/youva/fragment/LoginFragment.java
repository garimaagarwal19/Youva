package com.decurtis.youva.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.decurtis.youva.GoogleSignInModule;
import com.decurtis.youva.LoginCallback;
import com.decurtis.youva.R;
import com.decurtis.youva.ServiceFactory;
import com.decurtis.youva.model.UserDetails;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

/**
 * Created by Garima Chamaria on 20/12/18.
 */
public class LoginFragment extends Fragment {
    private View mView;
    private LoginCallback mActivityCallback;
    private SignInButton mSignInButton;
    private GoogleSignInModule googleSignInModule;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.login_fragment, container, false);
        mActivityCallback.showToolbar(false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSignInButton = (SignInButton) mView.findViewById(R.id.google_button);

        googleSignInModule = new GoogleSignInModule();
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performGoogleSignIn();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GoogleSignInModule.REQ_CODE && resultCode == Activity.RESULT_OK) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (task.isSuccessful()) {
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    if (null == account) {
                        return;
                    }
                    String email = account.getEmail();
                    String fullName = account.getDisplayName();
                    String id = account.getId();
                    String photoUrl = String.valueOf(account.getPhotoUrl());

                    saveDataToDatabase(fullName, email, photoUrl, id);
                    googleSignInModule.performGoogleSignOut();
                    mActivityCallback.loginComplete(fullName);

                } catch (ApiException e) {
                    e.printStackTrace();
                }
            } else if (null != task.getException()) {
                Log.e("Error : ", "Error while getting account details");
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mActivityCallback != null) mActivityCallback = null;
    }

    public void setInterface(LoginCallback activityCallback) {
        mActivityCallback = activityCallback;
    }

    private void performGoogleSignIn() {
        Intent intent = googleSignInModule.getGoogleSignInIntent();
        getActivity().startActivityForResult(intent, GoogleSignInModule.REQ_CODE);
    }

    private void saveDataToDatabase(String fullName, String email, String photoUrl, String id) {
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(email);
        if (photoUrl != null && photoUrl.length() > 0)
            userDetails.setImageURL(photoUrl);
        userDetails.setName(fullName);
        userDetails.setKey(id);
        ServiceFactory.getDatabaseManager().saveUserBasicData(userDetails);
        ServiceFactory.getSharedPreferencesManager().setLoggedInAccount(userDetails);
    }
}
