package com.decurtis.youva.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.decurtis.youva.ActivityCallback;
import com.decurtis.youva.MainApplication;
import com.decurtis.youva.R;
import com.decurtis.youva.ServiceFactory;
import com.decurtis.youva.model.UserDetails;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

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
    private Button mapbtn;


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
        mUserEmailId = mView. findViewById(R.id.text_loggedIn_Id);
        mapbtn = mView.findViewById(R.id.map);

        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityCallback.startMap();
            }
        });

        initializeData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getContext() == null) return;

        Place selectedPlace = PlacePicker.getPlace(getContext(), data);
        double a = selectedPlace.getLatLng().longitude;
        double b = selectedPlace.getLatLng().latitude;

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
    }
}
