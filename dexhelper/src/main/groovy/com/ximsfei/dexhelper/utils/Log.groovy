package com.ximsfei.dexhelper.utils

/**
 * Created by ximsfei on 2017/7/15.
 */
class Log {
    static TAG = "===========DexHelper==========="
    def static d(String msg) {
        println(TAG + msg)
    }

    def static d(String tag, String msg) {
        println(TAG + tag + " = " + msg)
    }
}
