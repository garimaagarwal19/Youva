package com.decurtis.youva.executor;


import android.os.Handler;
import android.os.Looper;

/**
 * Created by Garima Chamaria on 26/12/18.
 */
public class MainThreadImpl implements MainThread {
    private static MainThread sMainThread;

    private final Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static MainThread getInstance() {
        if(sMainThread == null)
            sMainThread = new MainThreadImpl();
        return sMainThread;
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}
