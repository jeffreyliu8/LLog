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
    private static boolean mShowBox = true;
    private static boolean mShowLineInfo = true;
    private static boolean mSave = true;

    private static final String PREFIX = "<[ ";
    private static final String POSTFIX = " ]>";

    public static void init(Context context, String tag, boolean doSave, boolean showBox, boolean showLineInfo, boolean logForProduction) {
        mContext = context;
        mTag = tag;
        mLogForProduction = false;
        mShowBox = showBox;
        mShowLineInfo = showLineInfo;
        mLogForProduction = logForProduction;
        mSave = doSave;
    }

    public static synchronized void v(String msg) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.v(mTag, PREFIX + msg + POSTFIX + lineInfo);
            } else {
                Log.v(mTag, msg + lineInfo);
            }
        }
        saveLog(msg, VERBOSE, null);
    }

    public static synchronized void v(String msg, Throwable throwable) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.v(mTag, PREFIX + msg + POSTFIX, throwable);
            } else {
                Log.v(mTag, msg + lineInfo, throwable);
            }
        }
        saveLog(msg, VERBOSE, throwable);
    }

    public static synchronized void d(String msg) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.d(mTag, PREFIX + msg + POSTFIX + lineInfo);
            } else {
                Log.d(mTag, msg + lineInfo);
            }
        }
        saveLog(msg, DEBUG, null);
    }

    public static synchronized void d(String msg, Throwable throwable) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.d(mTag, PREFIX + msg + POSTFIX + lineInfo, throwable);
            } else {
                Log.d(mTag, msg + lineInfo, throwable);
            }
        }
        saveLog(msg, DEBUG, throwable);
    }

    public static synchronized void i(String msg) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.i(mTag, PREFIX + msg + POSTFIX + lineInfo);
            } else {
                Log.i(mTag, msg + lineInfo);
            }
        }
        saveLog(msg, INFO, null);
    }

    public static synchronized void i(String msg, Throwable throwable) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.i(mTag, PREFIX + msg + POSTFIX + lineInfo, throwable);
            } else {
                Log.i(mTag, msg + lineInfo, throwable);
            }
        }
        saveLog(msg, INFO, throwable);
    }

    public static synchronized void w(String msg) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.w(mTag, PREFIX + msg + POSTFIX + lineInfo);
            } else {
                Log.w(mTag, msg + lineInfo);
            }
        }
        saveLog(msg, WARN, null);
    }

    public static synchronized void w(String msg, Throwable throwable) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.w(mTag, PREFIX + msg + POSTFIX + lineInfo, throwable);
            } else {
                Log.w(mTag, msg + lineInfo, throwable);
            }
        }
        saveLog(msg, WARN, throwable);
    }

    public static synchronized void w(Throwable throwable) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.w(mTag, throwable);
        }
        saveLog(null, WARN, throwable);
    }

    public static synchronized void e(String msg) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.e(mTag, PREFIX + msg + POSTFIX + lineInfo);
            } else {
                Log.e(mTag, msg + lineInfo);
            }
        }
        saveLog(msg, ERROR, null);
    }

    public static synchronized void e(String msg, Throwable throwable) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.e(mTag, PREFIX + msg + POSTFIX + lineInfo, throwable);
            } else {
                Log.e(mTag, msg + lineInfo, throwable);
            }
        }
        saveLog(msg, ERROR, throwable);
    }

    public static synchronized void wtf(String msg) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.wtf(mTag, PREFIX + msg + POSTFIX + lineInfo);
            } else {
                Log.wtf(mTag, msg + lineInfo);
            }
        }
        saveLog(msg, ASSERT, null);
    }

    public static synchronized void wtf(String msg, Throwable throwable) {
        String lineInfo = getLineInfo();
        if (BuildConfig.DEBUG || mLogForProduction) {
            if (mShowBox) {
                Log.wtf(mTag, PREFIX + msg + POSTFIX + lineInfo, throwable);
            } else {
                Log.wtf(mTag, msg + lineInfo, throwable);
            }
        }
        saveLog(msg, ASSERT, throwable);
    }

    public static synchronized void wtf(Throwable throwable) {
        if (BuildConfig.DEBUG || mLogForProduction) {
            Log.wtf(mTag, throwable);
        }
        saveLog(null, ASSERT, throwable);
    }

    private static void saveLog(final String msg, final int logLevel, final Throwable throwable) {
        if (mSave) {
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

    private static String getLineInfo() {
        if (mShowLineInfo) {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            StackTraceElement s = elements[4];
            return " @ " + s.getClassName() + "." + s.getMethodName() + "(" + s.getFileName() + ":" + s.getLineNumber() + ")";
        }
        return "";
    }


    //===builder

    /**
     * Builder class for the EasyPrefs instance. You only have to call this once in the Application
     * onCreate. And in the rest of the code base you can call Prefs.method name.
     */
    public final static class Builder {
        private String mTag;
        private Context mContext;
        private boolean mSave = true;
        private boolean mShowBox = true;
        private boolean mShowLineInfo = true;
        private boolean mLogForProduction = false;

        public Builder setContext(final Context context) {
            mContext = context;
            return this;
        }

        public Builder setTag(final String tag) {
            mTag = tag;
            return this;
        }

        public Builder enableSaving(final boolean enable) {
            mSave = enable;
            return this;
        }

        public Builder enableBorder(final boolean enable) {
            mShowBox = enable;
            return this;
        }

        public Builder enableLineInfo(final boolean enable) {
            mShowLineInfo = enable;
            return this;
        }

        public Builder enableProductionLogging(final boolean enable) {
            mLogForProduction = enable;
            return this;
        }

        /**
         * @throws IllegalArgumentException is thrown if the tag.length() > 23
         *                                  for Nougat (7.0) releases (API <= 23) and prior, there is no
         *                                  tag limit of concern after this API level.
         */
        public void build() {
            if (mContext == null) {
                throw new RuntimeException("Context not set, please set context.");
            }
            if (TextUtils.isEmpty(mTag)) {
                throw new IllegalArgumentException("empty or null tag");
            }
            if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M && mTag.length() > 23) {
                throw new IllegalArgumentException("tag can't be longer than 23 for Nougat (7.0) releases (API <= 23) and prior");
            }
            LLog.init(mContext, mTag, mSave, mShowBox, mShowLineInfo, mLogForProduction);
        }
    }
}