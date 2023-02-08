package com.example.structure.ui.whether

import android.view.View
import com.example.structure.R
import com.example.structure.data.model.Weather
import com.example.structure.databinding.WeatherListItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class WeatherItem(val dailyItem: Weather.DailyItem) : BindableItem<WeatherListItemBinding>() {

    override fun getLayout(): Int = R.layout.weather_list_item

    override fun initializeViewBinding(view: View): WeatherListItemBinding = WeatherListItemBinding.bind(view)

    override fun bind(viewBinding: WeatherListItemBinding, position: Int) {
        viewBinding.item = dailyItem
    }
}