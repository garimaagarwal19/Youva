package com.example.youva;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;

/**
 * Created by Garima Chamaria on 26/12/18.
 */
public class GoogleSignInModule implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    public static final int REQ_CODE = 9001;

    private GoogleApiClient mGoogleApiClient;

    public GoogleSignInModule() {
    }

    public Intent getGoogleSignInIntent() {
        String clientId = MainApplication.getContext().getResources().getString(R.string.client_id);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(clientId)
                .requestServerAuthCode(clientId, false)
                .requestEmail()
                .requestScopes(new Scope(Scopes.PROFILE))
                .build();

        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(MainApplication.getContext(), signInOptions);

        mGoogleApiClient = new GoogleApiClient.Builder(MainApplication.getContext())
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();
        mGoogleApiClient.connect();

        return mGoogleSignInClient.getSignInIntent();
    }

    public void performGoogleSignOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("Connection Successful", "");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("Connection Suspended", "");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Google Connection Fail", connectionResult.getErrorMessage());
    }
}
