package com.decurtis.youva.presenter;

import com.decurtis.youva.BasePresenter;
import com.decurtis.youva.SharedPreferenceManager;

/**
 * Created by Garima Chamaria on 09/01/19.
 */
public class ModeSelectionPresenter extends BasePresenter {

    private SharedPreferenceManager mSharedPreferenceManager;

    public ModeSelectionPresenter(SharedPreferenceManager sharedPreferenceManager) {
        mSharedPreferenceManager = sharedPreferenceManager;
    }

    public void setAppMode(int value) {
        mSharedPreferenceManager.setAppMode(value);
    }
}
