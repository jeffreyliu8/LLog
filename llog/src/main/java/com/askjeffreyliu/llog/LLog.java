package com.askjeffreyliu.llog;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import static android.util.Log.ASSERT;
import static android.util.Log.DEBUG;
import static android.util.Log.ERROR;
import static android.util.Log.INFO;
import static android.util.Log.VERBOSE;
import static android.util.Log.WARN;

public final class LLog {

    private static Context mContext;
    private static String mTag;
    private static boolean mLogForProduction = false;


    /**
     * @throws IllegalArgumentException is thrown if the tag.length() > 23
     *                                  for Nougat (7.0) releases (API <= 23) and prior, there is no
     *                                  tag limit of concern after this API level.
     */

    public static void setContext(Context context, String tag) {
        if (TextUtils.isEmpty(tag)) {
            throw new IllegalArgumentException("empty or null tag");
        }
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M && tag.length() > 23) {
            throw new IllegalArgumentException("tag can't be longer than 23 for Nougat (7.0) releases (API <= 23) and prior");
        }
        mContext = context;
        mTag = tag;
        mLogForProduction = false;
    }

    /**
     * every time you set logForProduction to true, a puppy dies
     */
    public static void setContext(Context context, String tag, boolean logForProduction) {
        setContext(context, tag);
        mLogForProduction = logForProduction;
    }

    public static synchronized void v(String msg) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.v(mTag, msg);
        }
        saveLog(msg, VERBOSE, null);
    }

    public static synchronized void v(String msg, Throwable throwable) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.v(mTag, msg, throwable);
        }
        saveLog(msg, VERBOSE, throwable);
    }

    public static synchronized void d(String msg) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.d(mTag, msg);
        }
        saveLog(msg, DEBUG, null);
    }

    public static synchronized void d(String msg, Throwable throwable) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.d(mTag, msg);
        }
        saveLog(msg, DEBUG, throwable);
    }

    public static synchronized void i(String msg) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.i(mTag, msg);
        }
        saveLog(msg, INFO, null);
    }

    public static synchronized void i(String msg, Throwable throwable) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.i(mTag, msg);
        }
        saveLog(msg, INFO, throwable);
    }

    public static synchronized void w(String msg) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.w(mTag, msg);
        }
        saveLog(msg, WARN, null);
    }

    public static synchronized void w(String msg, Throwable throwable) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.w(mTag, msg);
        }
        saveLog(msg, WARN, throwable);
    }

    public static synchronized void e(String msg) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.e(mTag, msg);
        }
        saveLog(msg, ERROR, null);
    }

    public static synchronized void e(String msg, Throwable throwable) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.e(mTag, msg);
        }
        saveLog(msg, ERROR, throwable);
    }

    public static synchronized void wtf(String msg) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.wtf(mTag, msg);
        }
        saveLog(msg, ASSERT, null);
    }

    public static synchronized void wtf(String msg, Throwable throwable) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.wtf(mTag, msg);
        }
        saveLog(msg, ASSERT, throwable);
    }

    private static void saveLog(final String msg, final int logLevel, final Throwable throwable) {
        final String threadName = Thread.currentThread().getName();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                MobileLogRoomDatabase db = MobileLogRoomDatabase.getDatabase(mContext);
                db.mobileLogDao().insert(new MobileLog(msg, threadName, logLevel, throwable == null ? null : throwable.toString()));
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