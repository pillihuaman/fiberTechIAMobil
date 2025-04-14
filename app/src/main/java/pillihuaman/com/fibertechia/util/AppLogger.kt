package pillihuaman.com.fibertechia.util

// File: utils/AppLogger.kt

import android.util.Log

object AppLogger {
    private const val TAG = "FiberTechIA"

    fun info(message: String) {
        Log.i(TAG, "[INFO] $message")
    }

    fun debug(message: String) {
        Log.d(TAG, "[DEBUG] $message")
    }

    fun warning(message: String) {
        Log.w(TAG, "[WARNING] $message")
    }

    fun error(message: String, throwable: Throwable? = null) {
        Log.e(TAG, "[ERROR] $message", throwable)
    }
}
