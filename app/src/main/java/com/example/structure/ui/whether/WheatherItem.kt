package com.example.structure.ui.whether

import android.view.View
import com.example.structure.R
import com.example.structure.databinding.ListItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class WeatherItem() : BindableItem<ListItemBinding>() {

    override fun getLayout(): Int = R.layout.list_item

    override fun initializeViewBinding(view: View): ListItemBinding = ListItemBinding.bind(view)

    override fun bind(viewBinding: ListItemBinding, position: Int) {

    }
}