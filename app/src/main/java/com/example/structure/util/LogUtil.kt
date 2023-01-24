package com.example.structure.util

import android.util.Log
import com.example.structure.BuildConfig

/**
 * 로그가 찍힌 부분을 클릭시 해당 라인으로 이동
 */
object LogUtil {
    fun log(tag: String?, message: String) {
        val filename = String.format(
            " (%s:%s)", Throwable().stackTrace[1].fileName, Throwable().stackTrace[1].lineNumber
        )
        val methodName = String.format(" %s() ", Throwable().stackTrace[1].methodName)
        Log.d(tag, methodName + message + filename)
    }
}