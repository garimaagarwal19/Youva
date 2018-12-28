package com.decurtis.youva.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.decurtis.youva.UserDetailCallback;
import com.decurtis.youva.R;

/**
 * Created by Garima Chamaria on 26/12/18.
 */

public class ACKFragment extends Fragment {
    public static final String TAG = ACKFragment.class.getSimpleName();
    private static final String FRAG_TITLE = "ACk";
    private UserDetailCallback mActivityCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ack_fragment, container, false);
        mActivityCallback.showToolbar(true);
        mActivityCallback.setNavigationAndTitle(FRAG_TITLE, false);
        return view;
    }

    public void setInterface(UserDetailCallback activityCallback) {
        mActivityCallback = activityCallback;
    }
}
