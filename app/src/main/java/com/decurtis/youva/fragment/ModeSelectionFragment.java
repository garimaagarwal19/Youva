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
import com.decurtis.youva.model.AppMode;

/**
 * Created by Garima Chamaria on 21/12/18.
 */
public class ModeSelectionFragment extends Fragment {
    public static final String TAG = ModeSelectionFragment.class.getSimpleName();

    private ModeSelectionCallback mActivityCallback;
    private String name;
    private TextView mHeading;
    private ImageView mBusinessPerson, mIndividual;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mode_selection_fragment, container, false);
        findAllIDs(view);
        name = getArguments().getString(AppConstants.NAME);
        mActivityCallback.showToolbar(true);
        mActivityCallback.setNavigationAndTitle(getString(R.string.app_name), false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] substring = name.split(" ");
        mHeading.setText("Hi " + substring[0] + "! " + AppConstants.SELECT_MODE);

        mBusinessPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserDetailsFragment(AppMode.BUSINESS.getValue());
            }
        });

        mIndividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserDetailsFragment(AppMode.INDIVIDUAL.getValue());
            }
        });
    }

    private void findAllIDs(View view) {
        mHeading = view.findViewById(R.id.heading);
        mBusinessPerson = view.findViewById(R.id.img_business_owner);
        mIndividual = view.findViewById(R.id.img_individual);
    }

    private void addUserDetailsFragment(int value) {
        ServiceFactory.getSharedPreferencesManager().setAppMode(value);
        mActivityCallback.addUserDetailsFragment();
    }

    @Override
    public void onDestroy() {
        mActivityCallback.showToolbar(false);
        mActivityCallback = null;
        super.onDestroy();
    }

    public void setInterface(ModeSelectionCallback activityCallback) {
        mActivityCallback = activityCallback;
    }
}
