package com.example.structure.ui.whether

import android.view.View
import com.example.structure.R
import com.example.structure.databinding.WeatherListItemHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class HeaderItem(
    private val cityName: String
) : BindableItem<WeatherListItemHeaderBinding>() {

    override fun getLayout(): Int = R.layout.weather_list_item_header

    override fun initializeViewBinding(view: View): WeatherListItemHeaderBinding =
        WeatherListItemHeaderBinding.bind(view)

    override fun bind(viewBinding: WeatherListItemHeaderBinding, position: Int) {
        viewBinding.city.text = cityName
    }
}