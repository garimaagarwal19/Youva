package com.decurtis.youva.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.decurtis.youva.ActivityCallback;
import com.decurtis.youva.GoogleSignInModule;
import com.decurtis.youva.R;
import com.google.android.gms.common.SignInButton;

/**
 * Created by Garima Chamaria on 20/12/18.
 */
public class LoginFragment extends Fragment {
    private View mView;
    private ActivityCallback mActivityCallback;
    private SignInButton mSignInButton;

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

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performGoogleSignIn();
            }
        });
    }

    public void setInterface(ActivityCallback activityCallback) {
        mActivityCallback = activityCallback;
    }

    private void performGoogleSignIn() {
        Intent intent = GoogleSignInModule.getInstance().getGoogleSignInIntent();
        getActivity().startActivityForResult(intent, GoogleSignInModule.REQ_CODE);
    }
}
