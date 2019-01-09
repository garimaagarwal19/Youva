package com.decurtis.youva;

import com.decurtis.youva.view.IView;

/**
 * Created by Garima Chamaria on 09/01/19.
 */
public class BasePresenter {
    IView mView;

    public void onAttach(IView view) {
        mView = view;
    }

    public IView getView() {
        return mView;
    }

    public void onDetach() {
        mView = null;
    }
}
