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

import com.decurtis.youva.ActivityCallback;
import com.decurtis.youva.DataEventListener;
import com.decurtis.youva.R;
import com.decurtis.youva.ServiceFactory;
import com.decurtis.youva.executor.ThreadExecutor;
import com.decurtis.youva.model.UserDetails;

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

        initializeData();
    }

    public void setInterface(ActivityCallback activityCallback) {
        mActivityCallback = activityCallback;
    }

    private void initializeData() {
        String id = ServiceFactory.getSharedPreferences().getLoggedInAccountKey();
        ServiceFactory.getDatabaseManager().getLoggedInAccount(id, new DataEventListener<UserDetails>() {

            @Override
            public void OnSuccess(final UserDetails object) {
                ThreadExecutor.getInstance().getMainThread().post(new Runnable() {
                    @Override
                    public void run() {
                        mUserEmailId.setText(object.getEmail());
                    }
                });
            }
        });
    }
}
