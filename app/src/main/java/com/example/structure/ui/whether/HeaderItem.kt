package com.example.structure.ui.whether

import android.view.View
import com.example.structure.R
import com.example.structure.databinding.ListItemHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

class HeaderItem(
    val city: String
) : BindableItem<ListItemHeaderBinding>() {

    override fun getLayout(): Int = R.layout.list_item_header

    override fun initializeViewBinding(view: View): ListItemHeaderBinding =
        ListItemHeaderBinding.bind(view)

    override fun bind(viewBinding: ListItemHeaderBinding, position: Int) {
        viewBinding.city.text = city
    }
}