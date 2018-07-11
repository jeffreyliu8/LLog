package com.askjeffreyliu.llog;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public final class LLog {

    private static Context mContext;
    private static String mTag;

    private static LLog INSTANCE = new LLog();

    // other instance variables can be here

    private LLog() {
    }

    public static LLog getInstance() {
        return (INSTANCE);
    }

    public static void setContext(Context context, String tag) {
        mContext = context;
        mTag = tag;
    }

    public static synchronized void d(String msg) {
        Log.d(mTag, msg);
        saveLog(msg);
    }

    public static synchronized void e(String msg) {
        Log.e(mTag, msg);
        saveLog(msg);
    }


    private static void saveLog(final String msg) {
        final String threadName = Thread.currentThread().getName();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                MobileLogRoomDatabase db = MobileLogRoomDatabase.getDatabase(mContext);
                db.mobileLogDao().insert(new MobileLog(msg, threadName));
                return null;
            }
        }.execute();
    }

    public LiveData<List<MobileLog>> getLogs() {
        MobileLogRoomDatabase db = MobileLogRoomDatabase.getDatabase(mContext);
        return db.mobileLogDao().getLogs();
    }
}