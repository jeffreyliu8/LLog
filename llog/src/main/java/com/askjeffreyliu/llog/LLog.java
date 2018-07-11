package com.askjeffreyliu.llog;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public final class LLog {

    private static Context mContext;
    private static String mTag;

    public static void setContext(Context context, String tag) {
        mContext = context;
        mTag = tag;
    }

    public static synchronized void d(String msg) {
        Log.d(mTag, msg);
        test(msg);
    }

    public static synchronized void e(String msg) {
        Log.e(mTag, msg);
        test(msg);
    }


    private static void test(final String msg) {
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
}