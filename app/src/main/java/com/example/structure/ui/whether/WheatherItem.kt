package com.example.structure.ui.whether

import android.view.View
import com.example.structure.R
import com.example.structure.data.vo.WeatherVo
import com.example.structure.databinding.ListItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class WeatherItem(val dailyItem: WeatherVo.DailyItem) : BindableItem<ListItemBinding>() {

    override fun getLayout(): Int = R.layout.list_item

    override fun initializeViewBinding(view: View): ListItemBinding = ListItemBinding.bind(view)

    override fun bind(viewBinding: ListItemBinding, position: Int) {
        viewBinding.item = dailyItem
    }
}