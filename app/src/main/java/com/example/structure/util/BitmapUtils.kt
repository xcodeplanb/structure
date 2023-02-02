package com.example.structure.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.media.ExifInterface
import android.view.View
import java.net.URL
import kotlin.jvm.Throws

fun getBitmapFromUrl(url: String): Bitmap? = try {
    URL(url).openStream().use {
        BitmapFactory.decodeStream(it)
    }
} catch (e: Exception) {
    null
}

@Throws(IllegalArgumentException::class)
fun getBitmapFromView(view: View?): Bitmap? {
    view?.let { view ->
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        val bitmap = Bitmap.createBitmap(
            view.width, view.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
    return null
}

fun getResizeBitmapWithMatrix(imageFilePath: String, maxLength: Int): Bitmap? {
    try {
        val bitmap = BitmapFactory.decodeFile(imageFilePath)
        val orientation = getOrientation(imageFilePath)
        val matrix = Matrix()
        matrix.postRotate(orientation)
        if (bitmap.height >= bitmap.width) {
            val scaleRatio = maxLength.toFloat() / bitmap.height.toFloat()
            matrix.postScale(scaleRatio,scaleRatio)
            return Bitmap.createBitmap(bitmap,0,0, bitmap.width, bitmap.height, matrix,true)
        } else {
            val scaleRatio = maxLength.toFloat() / bitmap.width.toFloat()
            matrix.postScale(scaleRatio,scaleRatio)
            return Bitmap.createBitmap(bitmap,0,0, bitmap.width, bitmap.height, matrix,true)
        }
    } catch (e: Exception) {
        return null
    }
}

fun getOrientation(filePath: String): Float {
    val orientationTag = ExifInterface(filePath).getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_NORMAL
    )
    val orientation = when (orientationTag) {
        ExifInterface.ORIENTATION_ROTATE_90 -> 90
        ExifInterface.ORIENTATION_ROTATE_180 -> 180
        ExifInterface.ORIENTATION_ROTATE_270 -> 270
        else -> 0
    }
    return orientation.toFloat()
}