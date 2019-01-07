package com.decurtis.youva;

import com.decurtis.youva.di.component.ActivityComponent;

/**
 * Created by Garima Chamaria on 26/12/18.
 */
public interface ActivityCallback {
    void showToolbar(boolean b);

    void setNavigationAndTitle(String string, boolean b);

    ActivityComponent getActivityComponent();

}
