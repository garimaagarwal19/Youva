package com.decurtis.youva;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Garima Chamaria on 21/12/18.
 */
public class UserFragment extends Fragment {
    private View mView;
    private String name;
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.userfragment, container, false);
        name = getArguments().getString("name");
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = mView.findViewById(R.id.text_Successfull);
        mTextView.setText(name + " Successfully Loggedin");
    }
}
