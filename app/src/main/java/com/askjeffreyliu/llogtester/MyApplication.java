package com.askjeffreyliu.llogtester;


import android.app.Application;

import com.askjeffreyliu.llog.LLog;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new LLog.Builder()
                .setContext(this) // required
                .setTag("LiuLog") // required
                .enableBorder(true) // default true, optional
                .enableLineInfo(true)// default true, optional
                .enableProductionLogging(false) // default false, optional. Every time you set this to true, a puppy dies
                .build();
    }
}
