package com.askjeffreyliu.llog;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public final class LLog {

    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;

    private static Context mContext;
    private static String mTag;

    public static void setContext(Context context, String tag) {
        mContext = context;
        mTag = tag;
    }

    public static synchronized void v(String msg) {
        Log.v(mTag, msg);
        saveLog(msg, VERBOSE);
    }

    public static synchronized void d(String msg) {
        Log.d(mTag, msg);
        saveLog(msg, DEBUG);
    }

    public static synchronized void i(String msg) {
        Log.i(mTag, msg);
        saveLog(msg, INFO);
    }

    public static synchronized void w(String msg) {
        Log.w(mTag, msg);
        saveLog(msg, WARN);
    }

    public static synchronized void e(String msg) {
        Log.e(mTag, msg);
        saveLog(msg, ERROR);
    }

    public static synchronized void wtf(String msg) {
        Log.wtf(mTag, msg);
        saveLog(msg, ASSERT);
    }

    private static void saveLog(final String msg, final int logLevel) {
        final String threadName = Thread.currentThread().getName();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                MobileLogRoomDatabase db = MobileLogRoomDatabase.getDatabase(mContext);
                db.mobileLogDao().insert(new MobileLog(msg, threadName, logLevel));
                return null;
            }
        }.execute();
    }

    public static LiveData<List<MobileLog>> getLogs() {
        MobileLogRoomDatabase db = MobileLogRoomDatabase.getDatabase(mContext);
        return db.mobileLogDao().getLogs();
    }

    public static void clearLogs() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                MobileLogRoomDatabase db = MobileLogRoomDatabase.getDatabase(mContext);
                db.mobileLogDao().deleteAll();
                return null;
            }
        }.execute();
    }
}