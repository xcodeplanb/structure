package com.example.structure.util

import android.text.*
import android.view.View.*
import android.webkit.*
import android.widget.*
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.structure.api.Resource
import com.example.structure.data.model.Weather

object BindingUtils {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        if (url.isNullOrEmpty().not()) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }

    @BindingAdapter("thumbnailImageUrl")
    @JvmStatic
    fun setThumbnailImageUrl(imageView: ImageView, url: String?) {
        if (url.isNullOrEmpty().not()) {
            Glide.with(imageView.context).load(url).thumbnail(0.25f).into(imageView)
        }
    }

    @BindingAdapter("showLoading")
    @JvmStatic
    fun ProgressBar.showLoading(state: Resource<Nothing>) {
        visibility = if (state is Resource.Loading)
            VISIBLE
        else
            GONE
    }

    @BindingAdapter("weatherImage")
    @JvmStatic
    fun setWeatherImage(imageView: ImageView, list: List<Weather.DailyItem.WeatherItem>?) {
        if (list.isNullOrEmpty().not()) {
            Glide.with(imageView.context).load(list?.get(0)?.iconUrl).into(imageView)
        }
    }

    @BindingAdapter("weatherText")
    @JvmStatic
    fun setWeatherText(textView: TextView, list: List<Weather.DailyItem.WeatherItem>?) {
        if (list.isNullOrEmpty().not()) {
            textView.text = list?.get(0)?.description
        }
    }
}
