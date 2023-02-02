package com.example.structure.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Display
import androidx.core.hardware.display.DisplayManagerCompat

// extension function to convert dp to equivalent pixels
fun Int.dpToPixels(context: Context):Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,this.toFloat(),context.resources.displayMetrics
)

// input float dp value to convert equivalent pixels
fun Float.dpToPixels(context: Context): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics
)

// extension function to convert pixels to equivalent dp
fun Int.pixelsToDp(context: Context): Float =
    this / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()

// input float pixels value to convert equivalent dp
fun Float.pixelsToDp(context: Context): Float =
    this / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()

fun getDeviceWidth(activity: Activity): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val defaultDisplay =
            DisplayManagerCompat.getInstance(activity).getDisplay(Display.DEFAULT_DISPLAY)
        val displayContext = activity.createDisplayContext(defaultDisplay!!)
        displayContext.resources.displayMetrics.widthPixels
    } else {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.widthPixels
    }
}

fun getDeviceHeight(activity: Activity): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val defaultDisplay =
            DisplayManagerCompat.getInstance(activity).getDisplay(Display.DEFAULT_DISPLAY)
        val displayContext = activity.createDisplayContext(defaultDisplay!!)
        displayContext.resources.displayMetrics.heightPixels
    } else {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.heightPixels
    }
}