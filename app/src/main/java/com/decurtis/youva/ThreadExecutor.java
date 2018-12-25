package com.decurtis.youva;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Garima Chamaria on 25/12/18.
 */
public class ThreadExecutor implements Executor {
    private static volatile ThreadExecutor mThreadExecutor;

    private ThreadPoolExecutor mThreadPoolExecutor;

    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private static final int CORE_POOL_SIZE = NUMBER_OF_CORES;
    private static final int MAX_POOL_SIZE = NUMBER_OF_CORES*2;
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();

    private ThreadExecutor() {
        long keeAlive = KEEP_ALIVE_TIME;
        mThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE, keeAlive, TIME_UNIT, WORK_QUEUE);
    }

    public static ThreadExecutor getInstance() {
        if(mThreadExecutor == null)
            mThreadExecutor = new ThreadExecutor();
        return mThreadExecutor;
    }

    @Override
    public void execute(@NonNull final Runnable runnable) {
        mThreadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        });
    }
}
