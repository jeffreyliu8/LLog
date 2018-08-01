# LLog
A simple android logger that print and save logs to db.

[![](https://jitpack.io/v/jeffreyliu8/LLog.svg)](https://jitpack.io/#jeffreyliu8/LLog)

Why LLog
----------------
Don't you want to have all your logs saved in sql lite db? And be able to process later on? This is your baby.
This is based on the simple android.util.Log.

Using LLog
----------------

### Setup
```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


##### Dependencies
```groovy
	dependencies {
	        implementation 'com.github.jeffreyliu8:LLog:0.0.8'
	}
```

A quick example is shown below on how to use it:

In your Application class onCreate() (java)
```java
        new LLog.Builder()
                .setContext(this) // required
                .setTag("LiuLog") // required
                .enableSaving(true) // default true, optional
                .enableBorder(true) // default true, optional
                .enableLineInfo(true)// default true, optional
                .enableProductionLogging(false) // default false, optional. Every time you set this to true, a puppy dies
                .build();
```

And some basic logging usage(kotlin):
```kotlin
        LLog.i("info")
        LLog.d("debug")
        LLog.e("error")
        LLog.w("warning")
        LLog.v("verbose")
        LLog.wtf("wtf")
```

![Output sample](https://github.com/jeffreyliu8/LLog/blob/master/screenshot.png)


Getting the logs, it's LiveData, see example in the ListViewModel source code(java)
```java
LiveData<List<MobileLog>> logs = LLog.getLogs();
```

Basic logging sql column include id, msg, thread name, log level, time stamp, check MobileLog class(kotlin)
```kotlin
val log: MobileLog = items!![position]
holder.myTextView.text = log.id.toString() + " " + log.message + " " + log.logLevel + " " + log.throwable + " " + log.thread
```
Delete all the logs in database(java)
```java
LLog.clearLogs()
```

Requirements
--------------
Requires a minimum SDK version of 14
