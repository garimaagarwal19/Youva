package com.decurtis.youva.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.zip.CheckedInputStream;

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
    private Button mBtnSubmit;

    private TextInputLayout mFirstNameLayout, mLastNameLayout, mPhoneNumberLayout;
    private EditText mFNameEdt, mLNameEdt,mPhoneEdit;

    private RadioGroup mGender;
    private RadioButton mMale, mFemale;
    private TextView mGenderError;

    private EditText mAddressEdt;
    private ImageView locationChecked;
    private TextView mAddLocation;
    private boolean isPersonalLocationAdded = false;

    private CheckBox CB1, CB2, CB3, CB4;
    private TextView mAddInterest;
    private boolean isInterestSelected = false;

    private TextInputLayout mBusinessNameLayout;
    private EditText mBusinessNameEdt;
    private EditText mBusinesssAddressEdt;
    private TextView mAddBusinessLocation;
    private boolean isBusinessLocationAdded = false;
    private ImageView businessLocationChecked;

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

        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateDetails())
                    saveDataToDatabase();
                else
                    Toast.makeText(MainApplication.getContext(), MainApplication.getContext().getResources().getString(R.string.str_error_toast_msg),
                            Toast.LENGTH_SHORT).show();
            }
        });

        setEditTextChangeListeners();
    }

    public void findAllIDs() {
        mUserImage = mView.findViewById(R.id.img_user);
        mUserEmailId = mView.findViewById(R.id.text_loggedIn_Id);
        mLocationView = mView.findViewById(R.id.img_location);
        mBusinessLocationView = mView.findViewById(R.id.img_business_location);

        mBusinessDetailsText = mView.findViewById(R.id.text_business_details);
        mBusinessLayout = mView.findViewById(R.id.business_layout);

        mFirstNameLayout = mView.findViewById(R.id.input_layout_fname);
        mLastNameLayout = mView.findViewById(R.id.input_layout_lname);
        mPhoneNumberLayout = mView.findViewById(R.id.input_layout_phonenumber);
        mFNameEdt = mView.findViewById(R.id.editText_fname);
        mLNameEdt = mView.findViewById(R.id.editText_lname);
        mPhoneEdit = mView.findViewById(R.id.editText_phoneNumber);

        mGender = mView.findViewById(R.id.rg_gender);
        mGenderError = mView.findViewById(R.id.text_error_gender);

        mAddressEdt = mView.findViewById(R.id.edit_address);
        mAddLocation = mView.findViewById(R.id.txt_location);
        locationChecked = mView.findViewById(R.id.img_checked);

        //TODO : Need to create this checklist dynamically
        CB1 = mView.findViewById(R.id.checkbox1);
        CB2 = mView.findViewById(R.id.checkbox2);
        CB3 = mView.findViewById(R.id.checkbox3);
        CB4 = mView.findViewById(R.id.checkbox4);
        mAddInterest = mView.findViewById(R.id.txt_select_interest);

        mBusinessNameLayout = mView.findViewById(R.id.input_layout_businessName);
        mBusinessNameEdt = mView.findViewById(R.id.edittext_businessName);
        mBusinesssAddressEdt = mView.findViewById(R.id.edit_businessAddress);
        mAddBusinessLocation = mView.findViewById(R.id.txt_location_business);
        businessLocationChecked = mView.findViewById(R.id.img_checked_business);

        mBtnSubmit = mView.findViewById(R.id.btn_submit);
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
                locationChecked.setVisibility(View.VISIBLE);
                if(!isPersonalLocationAdded) {
                    isPersonalLocationAdded = true;
                    mAddLocation.setError(null);
                    mAddLocation.setTextColor(MainApplication.getContext().getResources().getColor(R.color.colorHintText));
                }

                break;
            case BUSINESS_LOCATION:
                double c = selectedPlace.getLatLng().longitude;
                double d = selectedPlace.getLatLng().latitude;
                businessLocationChecked.setVisibility(View.VISIBLE);
                if(!isBusinessLocationAdded) {
                    isBusinessLocationAdded = true;
                    mAddBusinessLocation.setError(null);
                    mAddBusinessLocation.setTextColor(MainApplication.getContext().getResources().getColor(R.color.colorHintText));
                }
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

    private boolean validateDetails() {
        boolean isValidated = true;
        Resources resources = MainApplication.getContext().getResources();
        if(mFNameEdt.getText().toString().length() == 0) {
            mFirstNameLayout.setError(resources.getString(R.string.str_error_fname));
            isValidated = false;
        } if(mLNameEdt.getText().toString().length() == 0) {
            mLastNameLayout.setError(resources.getString(R.string.str_error_lname));
            isValidated = false;
        } if(mPhoneEdit.getText().toString().length() == 0) {
            mPhoneNumberLayout.setError(resources.getString(R.string.str_error_phone));
            isValidated = false;
        } if(mGender.getCheckedRadioButtonId() == -1) {
            mGenderError.setVisibility(View.VISIBLE);
            isValidated = false;
        } if(mAddressEdt.getText().toString().length() == 0) {
            mAddressEdt.setError(resources.getString(R.string.str_error_address));
            isValidated = false;
        } if(!isPersonalLocationAdded) {
            mAddLocation.setError(resources.getString(R.string.str_error_location));
            mAddLocation.setTextColor(resources.getColor(android.R.color.holo_red_light));
            isValidated = false;
        } if(!CB1.isChecked() && !CB2.isChecked() && !CB3.isChecked() && !CB4.isChecked()) {
            isInterestSelected = false;
            mAddInterest.setError(resources.getString(R.string.str_error_interest));
            mAddInterest.setTextColor(resources.getColor(android.R.color.holo_red_light));
            isValidated = false;
        }

        if(ServiceFactory.getSharedPreferences().getAppMode() == AppMode.BUSINESS.getValue()) {
            if(mBusinessNameEdt.getText().toString().length() == 0) {
                mBusinessNameLayout.setError(resources.getString(R.string.str_error_bName));
                isValidated = false;
            } if(mBusinesssAddressEdt.getText().toString().length() == 0) {
                mBusinesssAddressEdt.setError(resources.getString(R.string.str_error_address));
                isValidated = false;
            } if(!isBusinessLocationAdded) {
                mAddBusinessLocation.setError(resources.getString(R.string.str_error_location));
                mAddBusinessLocation.setTextColor(resources.getColor(android.R.color.holo_red_light));
                isValidated = false;
            }
        }
        return isValidated;
    }

    private void setEditTextChangeListeners() {
        mFNameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mFirstNameLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mLNameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mLastNameLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPhoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPhoneNumberLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mGenderError.setVisibility(View.GONE);
            }
        });

        CB1.setOnCheckedChangeListener(mOnCheckedChangeListener);
        CB2.setOnCheckedChangeListener(mOnCheckedChangeListener);
        CB3.setOnCheckedChangeListener(mOnCheckedChangeListener);
        CB4.setOnCheckedChangeListener(mOnCheckedChangeListener);

        mBusinessNameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mBusinessNameLayout.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener= new CompoundButton.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(!isInterestSelected) {
                isInterestSelected = true;
                mAddInterest.setError(null);
                mAddInterest.setTextColor(MainApplication.getContext().getResources().getColor(R.color.colorHintText));
            }
        }
    };

    private void saveDataToDatabase() {
    }
}
