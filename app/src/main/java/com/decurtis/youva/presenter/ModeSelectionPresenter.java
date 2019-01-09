package com.decurtis.youva.presenter;

import android.view.View;

import com.decurtis.youva.BasePresenter;
import com.decurtis.youva.SharedPreferenceManager;

import javax.inject.Inject;

/**
 * Created by Garima Chamaria on 09/01/19.
 */
public class ModeSelectionPresenter extends BasePresenter{
    private View mView;

    @Inject
    SharedPreferenceManager mSharedPreferenceManager;

    public void setAppMode(int value) {
        mSharedPreferenceManager.setAppMode(value);
    }
}
