package com.decurtis.youva.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.decurtis.youva.ActivityCallback;
import com.decurtis.youva.R;

/**
 * Created by Garima Chamaria on 26/12/18.
 */
public class ACKFragment extends Fragment{
    public static final String TAG = ACKFragment.class.getSimpleName();
    public static final String FRAG_TITLE = "ACk";
    private View mView;
    private ActivityCallback mActivityCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.ack_fragment, container, false);
        mActivityCallback.showToolbar(true);
        mActivityCallback.setNavigationAndTitle(FRAG_TITLE, false);
        return mView;
    }

    public void setInterface(ActivityCallback activityCallback) {
        mActivityCallback = activityCallback;
    }
}
