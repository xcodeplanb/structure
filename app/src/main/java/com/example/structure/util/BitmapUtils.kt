package com.example.structure.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
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