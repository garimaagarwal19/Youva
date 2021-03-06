package com.example.youva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youva.fragment.ACKFragment;
import com.example.youva.fragment.LoginFragment;
import com.example.youva.fragment.ModeSelectionFragment;
import com.example.youva.fragment.UserDetailsFragment;
import com.example.youva.model.AppMode;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mBackNavigation;

    private final ModeSelectionCallback mModeSelectionCallback = new ModeSelectionCallback() {
        @Override
        public void showToolbar(boolean b) {
            MainActivity.this.showToolbar(b);
        }

        @Override
        public void setNavigationAndTitle(String string, boolean b) {
            MainActivity.this.setNavigationAndTitle(string, b);
        }

        @Override
        public void addUserDetailsFragment() {
            MainActivity.this.addUserDetailsFragment();
        }
    };

    private final UserDetailCallback mUserDetailCallback = new UserDetailCallback() {
        @Override
        public void openACkFragment() {
            addACKFragment();
        }

        @Override
        public void startMap(int requestCode) {
            MainActivity.this.startMap(requestCode);
        }

        @Override
        public void showToolbar(boolean b) {
            MainActivity.this.showToolbar(b);
        }

        @Override
        public void setNavigationAndTitle(String string, boolean b) {
            MainActivity.this.setNavigationAndTitle(string, b);
        }

    };

    private final LoginCallback loginCallback  = new LoginCallback() {
        @Override
        public void loginComplete(String name) {
            addModeSelectionFragment(name);
        }

        @Override
        public void showToolbar(boolean b) {
            MainActivity.this.showToolbar(b);
        }

        @Override
        public void setNavigationAndTitle(String string, boolean b) {
            MainActivity.this.setNavigationAndTitle(string, b);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllIds();
        initComponents();
        if (ServiceFactory.getSharedPreferencesManager().getAppMode() == AppMode.DEFAULT.getValue() ||
                !ServiceFactory.getSharedPreferencesManager().getIsProfileCreated())
            addLoginFragment();
        else
            addACKFragment();
    }

    private void findAllIds() {
        mToolbar = findViewById(R.id.toolbar);
        mTitle = findViewById(R.id.title);
        mBackNavigation = findViewById(R.id.img_back);
    }

    private void initComponents() {
        mToolbar.inflateMenu(R.menu.menu);
        mToolbar.setOnMenuItemClickListener(mOnMenuItemClickListener);
        mBackNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popBackStack();
            }
        });
    }

    private void addLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setInterface(loginCallback);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_container, loginFragment).commit();
    }

    private void addModeSelectionFragment(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.NAME, name);
        ModeSelectionFragment modeSelectionFragment = new ModeSelectionFragment();
        modeSelectionFragment.setInterface(mModeSelectionCallback);
        modeSelectionFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_container, modeSelectionFragment).commit();
    }

    private void addUserDetailsFragment() {
        UserDetailsFragment userDetailsFragment = new UserDetailsFragment();
        userDetailsFragment.setInterface(mUserDetailCallback);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_container, userDetailsFragment, UserDetailsFragment.TAG).
                addToBackStack(UserDetailsFragment.TAG).commit();
    }

    private void addACKFragment() {
        FragmentManager fm = getSupportFragmentManager();
        while(fm.getBackStackEntryCount() > 0)
            fm.popBackStackImmediate();

        ACKFragment ackFragment = new ACKFragment();
        ackFragment.setInterface(mUserDetailCallback);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_container, ackFragment, ACKFragment.TAG).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment: fragments) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }



    public void showToolbar(boolean needToShow) {
        if (needToShow)
            mToolbar.setVisibility(View.VISIBLE);
        else
            mToolbar.setVisibility(View.GONE);
    }

    public void setNavigationAndTitle(String title, boolean showNavigation) {
        if (showNavigation)
            mBackNavigation.setVisibility(View.VISIBLE);
        else
            mBackNavigation.setVisibility(View.GONE);
        mTitle.setText(title);
    }

    final Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.sign_out:
                    ServiceFactory.getSharedPreferencesManager().invalidate();
                    FragmentManager fm = getSupportFragmentManager();
                    while (fm.getBackStackEntryCount() > 0)
                        fm.popBackStackImmediate();
                    addLoginFragment();
                    break;

                default: //do nothing
                    break;
            }
            return true;
        }
    };

    private void startMap(int requestCode) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(MainActivity.this), requestCode);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    private void popBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() >= 1)
            fm.popBackStack();
        else
            this.finish();
    }
}
