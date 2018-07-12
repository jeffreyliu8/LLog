# LLog
A simple android logger that print and save logs to db.





Why LLog
----------------
Don't you want to have all your logs saved in sql lite db? And be able to process later on? This is your baby.

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
	        implementation 'com.github.jeffreyliu8:LLog:0.0.6'
	}
```

A quick example is shown below on how to use it:

In your Application class onCreate()
```java
        new LLog.Builder()
                .setContext(this) // required
                .setTag("LiuLog") // required
                .enableBorder(true) // default true, optional
                .enableLineInfo(true)// default true, optional
                .enableProductionLogging(false) // default false, optional. Every time you set this to true, a puppy dies
                .build();
```

And actually logging:
```java
        LLog.i("info")
        LLog.d("debug")
        LLog.e("error")
        LLog.w("warning")
        LLog.v("verbose")
        LLog.wtf("wtf")
```

07-12 14:12:48.867 16721-16721/com.askjeffreyliu.llogtester I/LiuLog: <[ info ]> @ com.askjeffreyliu.llogtester.MainActivity$onCreate$1.onClick(MainActivity.kt:36)
07-12 14:12:48.869 16721-16721/com.askjeffreyliu.llogtester D/LiuLog: <[ debug ]> @ com.askjeffreyliu.llogtester.MainActivity$onCreate$1.onClick(MainActivity.kt:37)
07-12 14:12:48.869 16721-16721/com.askjeffreyliu.llogtester E/LiuLog: <[ error ]> @ com.askjeffreyliu.llogtester.MainActivity$onCreate$1.onClick(MainActivity.kt:38)
07-12 14:12:48.869 16721-16721/com.askjeffreyliu.llogtester W/LiuLog: <[ warning ]> @ com.askjeffreyliu.llogtester.MainActivity$onCreate$1.onClick(MainActivity.kt:39)
07-12 14:12:48.869 16721-16721/com.askjeffreyliu.llogtester V/LiuLog: <[ verbose ]> @ com.askjeffreyliu.llogtester.MainActivity$onCreate$1.onClick(MainActivity.kt:40)
07-12 14:12:48.870 16721-16721/com.askjeffreyliu.llogtester E/LiuLog: <[ wtf ]> @ com.askjeffreyliu.llogtester.MainActivity$onCreate$1.onClick(MainActivity.kt:41)


Getting the logs, it's LiveData, see example in the ListViewModel source code
```java
LiveData<List<MobileLog>> logs = LLog.getLogs();
```

Delete all the logs in database
```java
LLog.clearLogs()
```

Requirements
--------------
Requires a minimum SDK version of 14

Developed By
-------
Jeffrey Liu - <jeffreyliu8@gmail.com>

<a href="https://www.linkedin.com/in/jeffrey-liu-08a0b936">

License
-------

    Copyright 2018 Jeffrey Liu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
