# CSnackBar[ ![Download](https://api.bintray.com/packages/chathurahettiarachchi/maven/CSnackBar/images/download.svg) ](https://bintray.com/chathurahettiarachchi/maven/CSnackBar/_latestVersion)
This is a wrapper for android Snackbar. Which giving support to change Snackbar color, duration, message or even it's content view with a custom view.

![csnackbar](https://cloud.githubusercontent.com/assets/13764097/22288484/0942e32e-e31d-11e6-9630-2132379127f7.jpg)

This library contain following functions,

* Snackbar - alert type
* Snackbar - set custom duration
* Snackbar - custom contentview
* Snackbar - binding views
* Snackbar - custom message

####Let's take a look how to add this to your project

For the android project just include the following dependency inside you build.gradle's depedency list.

Gradle
------
```
repositories {
  jcenter()
}

dependencies {
    ...
    compile 'com.chootdev:csnackbar:1.0.0'
}
```

if you using maven use following
Maven
------
```
<dependency>
  <groupId>com.chootdev</groupId>
  <artifactId>csnackbar</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

After setup installing lib to your project you just need only to call it using just few lines of code. It will return you a string with the results.

Usage
-----
To show sample Snackbar
```java
Snackbar.with(this,null)
        .type(Type.SUCCESS)
        .message("Profile updated successfully!")
        .duration(Duration.SHORT)
        .show();
```

This contain multiple pre-defined types as alert view background.
```java
Type.SUCCESS  // Green color alert to show ok, success, approved type functions
Type.ERROR    // Red color alert to show error, failer type functions
Type.UPDATE   // Grey color alert to show update take place, working type functions
Type.WARNING  // Orange color alert to show alert type functions

Type.CUSTOM   // This can use to set custom color as background
```

Let's take a look, how to set custom color as background
```java
Snackbar.with(this,null)
        .type(Type.CUSTOM, 0xff00A7A5)
        .message("This is custom color!")
        .duration(Duration.LONG)
        .show();
```

We can use either pre-defined durations or custom duration as follow,
```java
Duration.SHORT      // Show period of time
Duration.LONG       // Long time ( not more than 10 sec )
Duration.INFINITE   // Snackbar stay until we call dismiss

Duration.CUSTOM     // Use to set custom duration
```

Let's see how to a custom view to the Snackbar view
```java
View view = getLayoutInflater().inflate(R.layout.custom_view, null);

Snackbar.with(this,null)
        .type(Type.UPDATE)
        .contentView(view, 76)
        .duration(Duration.SHORT)
        .show();
        
// since this is a wrapper lib, we need to send your custom view (layout resource) height in "dp", when setting view to the Snackbar.
```
When you are using custom view, you can/need to, change any view's settings before you set it as custom view to the Snackbar.

i.e :
```java
View view = getLayoutInflater().inflate(R.layout.custom_view, null);

TextView tv = (TextView) view.findViewById(R.id.sample);
```

Limitations
-----------
* Currently min SDK is set to 16

Output Generated
----------------
![ezgif com-video-to-gif](https://cloud.githubusercontent.com/assets/13764097/22289223/891662e4-e320-11e6-8828-2d334b1d379a.gif)

Changelog
---------
* **1.0.0**
    * Stable the release with sample code
* **0.0.1**
    * Initial release
    

## Author

Chathura Hettiarachchi, chathura93@yahoo.com

# License
Copyright 2016 Chathura Hettiarachchi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
