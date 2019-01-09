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
import com.decurtis.youva.di.component.LoginComponent;
import com.decurtis.youva.di.module.LoginFragmentModule;
import com.decurtis.youva.presenter.LoginPresenter;
import com.decurtis.youva.view.IView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

/**
 * Created by Garima Chamaria on 20/12/18.
 */
public class LoginFragment extends Fragment implements IView {
    private View mView;
    private LoginCallback mActivityCallback;

    @Inject
    GoogleSignInModule googleSignInModule;
    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginComponent loginComponent = mActivityCallback.getActivityComponent().plusLoginComponent(new LoginFragmentModule());
        loginComponent.inject(this);
    }

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

        mLoginPresenter.onAttach(this);

        SignInButton mSignInButton = mView.findViewById(R.id.google_button);

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
        Activity activity = getActivity();
        if(activity != null)
            activity.startActivityForResult(intent, GoogleSignInModule.REQ_CODE);
    }

    private void saveDataToDatabase(String fullName, String email, String photoUrl, String id) {
        mLoginPresenter.saveDataToDatabase(fullName, email, photoUrl, id);
    }
}
