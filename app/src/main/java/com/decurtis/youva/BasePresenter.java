package com.decurtis.youva;

import android.view.View;

/**
 * Created by Garima Chamaria on 09/01/19.
 */
public class BasePresenter {
    View mView;

    public void onAttach(View view) {
        mView = view;
    }

    public View getView() {
        return mView;
    }

    public void onDetach() {
        mView = null;
    }
}
