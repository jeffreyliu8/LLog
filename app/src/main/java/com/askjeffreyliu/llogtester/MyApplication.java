package com.askjeffreyliu.llogtester;


import android.app.Application;

import com.askjeffreyliu.llog.LLog;


public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        LLog.setContext(this, "JLOG");
    }
}
