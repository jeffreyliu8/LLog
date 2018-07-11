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
	        implementation 'com.github.jeffreyliu8:LLog:0.0.5'
	}
```

A quick example is shown below on how to use it:

In your Application class
```java
LLog.setContext(this, "JLOG");
```

And for just black and white image:
```java
LLog.d("test")
LLog.e("error")
```
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
