package com.decurtis.youva;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.decurtis.youva.fragment.LoginFragment;
import com.decurtis.youva.fragment.ModeSelectionFragment;
import com.decurtis.youva.model.UserDeails;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    private Toolbar mToolbar;
    private TextView mTitle, mLogoutText;
    private ImageView mBackNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllIds();
        initComponents();
        addLoginFragment();
    }

    private void findAllIds() {
        mToolbar = findViewById(R.id.toolbar);
        mLogoutText = findViewById(R.id.text_signOut);
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

        int appMode = SharedPrefManager.getInstance(getApplicationContext()).getAppMode();
//        if(appMode == 0)
//            mLogoutText.setVisibility(View.GONE);
//        else
//            mLogoutText.setVisibility(View.VISIBLE);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void addLoginFragment() {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_container, new LoginFragment()).commit();
    }

    public void addModeSelectionFragment(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.NAME, name);
        ModeSelectionFragment modeSelectionFragment = new ModeSelectionFragment();
        modeSelectionFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_container, modeSelectionFragment).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LoginFragment.REQ_CODE && resultCode == Activity.RESULT_OK) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (task.isSuccessful()) {
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    if (null == account) {
                        return;
                    }
                    String email = account.getEmail();
                    String fullName = account.getDisplayName();
                    String id = account.getId();
                    String photoUrl = String.valueOf(account.getPhotoUrl());
                    addModeSelectionFragment(fullName);

                    saveDataToDatabase(fullName, email, photoUrl, id);

                } catch (ApiException e) {
                    e.printStackTrace();
                }
            } else if (null != task.getException()) {
                Log.e("Error : ", "Error while getting account details");
            }
        } else {
            Log.e("Error : ", "Error While Google Login");
        }
    }

    private void saveDataToDatabase(String fullName, String email, String photoUrl, String id) {
        UserDeails userDeails = new UserDeails();
        userDeails.setEmail(email);
        if (photoUrl != null && photoUrl.length() > 0)
            userDeails.setImageURL(photoUrl);
        userDeails.setName(fullName);
        userDeails.setKey(id);
        ServiceFactory.getDatabaseManager().saveUserBasicData(userDeails);
    }

    public void showToolbar(boolean needToShow) {
        if (needToShow)
            mToolbar.setVisibility(View.VISIBLE);
        else
            mToolbar.setVisibility(View.GONE);
    }

    public void setNavigationAndTitle(String title, boolean showNavigation) {
        if(showNavigation)
            mBackNavigation.setVisibility(View.VISIBLE);
        else
            mBackNavigation.setVisibility(View.GONE);
        mTitle.setText(title);
    }

    Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.sign_out: //perform logout here
                    break;

                default: //do nothing
            }
            return true;
        }
    };

    private void popBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        if(fm.getBackStackEntryCount() >= 1)
            fm.popBackStack();
        else
            this.finish();
    }
}
