package com.decurtis.youva.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.decurtis.youva.AppConstants;
import com.decurtis.youva.ModeSelectionCallback;
import com.decurtis.youva.R;
import com.decurtis.youva.ServiceFactory;

/**
 * Created by Garima Chamaria on 21/12/18.
 */
public class ModeSelectionFragment extends Fragment {
    public static final String TAG = ModeSelectionFragment.class.getSimpleName();

    private ModeSelectionCallback mModeSelectionCallback;
    private View mView;
    private String name;
    private TextView mHeading;
    private ImageView mBusinessPerson, mIndividual;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.mode_selection_fragment, container, false);
        name = getArguments().getString(AppConstants.NAME);
        mModeSelectionCallback.showToolbar(true);
        mModeSelectionCallback.setNavigationAndTitle(getString(R.string.app_name), false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findAllIDs();

        String[] substring = name.split(" ");
        mHeading.setText("Hi " + substring[0] +"! "+ AppConstants.SELECT_MODE);

        mBusinessPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserDetailsFragment();
                ServiceFactory.getDatabaseManager();
            }
        });

        mIndividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserDetailsFragment();
            }
        });
    }

    private void findAllIDs() {
        mHeading = mView.findViewById(R.id.heading);
        mBusinessPerson = mView.findViewById(R.id.img_business_owner);
        mIndividual = mView.findViewById(R.id.img_individual);
    }

    private void addUserDetailsFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_container, new UserDetailsFragment(), UserDetailsFragment.TAG).addToBackStack(TAG).commit();
    }

    @Override
    public void onDestroy() {
        mModeSelectionCallback.showToolbar(false);
        super.onDestroy();
    }


    public void setInterface(ModeSelectionCallback modeSelectionCallback) {
        mModeSelectionCallback = modeSelectionCallback;
    }
}
