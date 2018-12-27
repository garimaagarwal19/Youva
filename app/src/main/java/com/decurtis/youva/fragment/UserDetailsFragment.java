package com.decurtis.youva.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.decurtis.youva.ActivityCallback;
import com.decurtis.youva.MainApplication;
import com.decurtis.youva.R;
import com.decurtis.youva.ServiceFactory;
import com.decurtis.youva.model.AppMode;
import com.decurtis.youva.model.UserDetails;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.w3c.dom.Text;

/**
 * Created by Garima Chamaria on 25/12/18.
 */
public class UserDetailsFragment extends Fragment {
    public static final String TAG = UserDetailsFragment.class.getSimpleName();
    public static final String FRAG_TITLE = "User Details";

    private View mView;
    private ActivityCallback mActivityCallback;

    private ImageView mUserImage;
    private TextView mUserEmailId;
    private ImageView mLocationView;
    private ImageView mBusinessLocationView;

    private static final int INDIVIDUAL_LOCATION = 3;
    private static final int BUSINESS_LOCATION = 1;

    private TextView mBusinessDetailsText;
    private LinearLayout mBusinessLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.user_details_fragment, container, false);
        mActivityCallback.showToolbar(true);
        mActivityCallback.setNavigationAndTitle(FRAG_TITLE, true);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUserImage = mView.findViewById(R.id.img_user);
        mUserEmailId = mView.findViewById(R.id.text_loggedIn_Id);
        mLocationView = mView.findViewById(R.id.img_location);
        mBusinessLocationView = mView.findViewById(R.id.img_business_location);

        findAllIDs();

        mLocationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityCallback.startMap(INDIVIDUAL_LOCATION);
            }
        });

        mBusinessLocationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityCallback.startMap(BUSINESS_LOCATION);
            }
        });
        initializeData();
    }

    public void findAllIDs() {
        mBusinessDetailsText = (TextView) mView.findViewById(R.id.text_business_details);
        mBusinessLayout = (LinearLayout) mView.findViewById(R.id.business_layout);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getContext() == null) return;
        if (resultCode != Activity.RESULT_OK) return;
        Place selectedPlace = PlacePicker.getPlace(getContext(), data);
        switch (requestCode) {
            case INDIVIDUAL_LOCATION:
                double a = selectedPlace.getLatLng().longitude;
                double b = selectedPlace.getLatLng().latitude;
                break;
            case BUSINESS_LOCATION:
                double c = selectedPlace.getLatLng().longitude;
                double d = selectedPlace.getLatLng().latitude;
                break;
            default:
                break;
        }
    }

    public void setInterface(ActivityCallback activityCallback) {
        mActivityCallback = activityCallback;
    }

    private void initializeData() {
        UserDetails userDetails = ServiceFactory.getSharedPreferences().getLoggedInAccount();
        mUserEmailId.setText(userDetails.getEmail());

        String url = userDetails.getImageURL();
        if (!TextUtils.isEmpty(url))
            Glide.with(MainApplication.getContext()).load(url).into(mUserImage);

        int appMode = ServiceFactory.getSharedPreferences().getAppMode();
        if(appMode != AppMode.BUSINESS.getValue()) {
            mBusinessDetailsText.setVisibility(View.GONE);
            mBusinessLayout.setVisibility(View.GONE);
        }
    }
}
