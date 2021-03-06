# DexHelper

* [介绍](#介绍)
* [接入](#接入)
  * [依赖](#依赖)
  * [应用](#应用)
  * [运行](#运行)
* [LICENSE](#license-apache)

## 介绍

一款分析Dex文件的Gradle插件，在APK打包结束后，输出APK包中的dex文件相关信息: 方法数等。

## 接入

### 依赖 

```gradle
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
        // 添加依赖
        classpath 'com.ximsfei:dexhelper:0.0.1'
    }
}
```

### 应用

```gradle
apply plugin: 'com.android.application'
// 应用插件
apply plugin: 'com.ximsfei.dexhelper'
```

### 运行

```
$ ./gradlew assemble -q
  DexHelper: apk file -> app-debug.apk
  DexHelper: dex file -> classes.dex
  DexHelper: method size -> 27022
  DexHelper: apk file -> app-release-unsigned.apk
  DexHelper: dex file -> classes.dex
  DexHelper: method size -> 27021
```
 
*注: 默认assemble\*为assembleDebug或assembleRelease*

## [License Apache](LICENSE)
