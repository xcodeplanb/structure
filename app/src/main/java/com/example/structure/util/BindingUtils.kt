package com.example.structure.util

import android.text.*
import android.view.View
import android.view.View.*
import android.webkit.*
import android.widget.*
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.structure.api.Resource
import com.example.structure.data.vo.WeatherVo

object BindingUtils {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, list: List<WeatherVo.DailyItem.WeatherItem>?) {
        if (!list.isNullOrEmpty()) {
            Glide.with(imageView.context).load(list[0].iconUrl).into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("bindText")
    fun setImageUrl(textView: TextView, list: List<WeatherVo.DailyItem.WeatherItem>?) {
        if (!list.isNullOrEmpty()) {
            textView.text = list[0].description
        }
    }

    @JvmStatic
    @BindingAdapter("showLoading")
    fun ProgressBar.showLoading(state: Resource<Nothing>) {
        visibility = if (state is Resource.Loading)
            VISIBLE
        else
            GONE
    }
}
