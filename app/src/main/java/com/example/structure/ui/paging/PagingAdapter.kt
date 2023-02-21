package com.example.structure.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.structure.data.model.GithubUser
import com.example.structure.databinding.FragmentPagingSeparatorItemBinding
import com.example.structure.databinding.FragmentPagingTextItemBinding

class PagingAdapter(val viewModel: PagingViewModel, val callback: (GithubUser.ItemsItem?) -> Unit) :
    PagingDataAdapter<PagingUiModel, RecyclerView.ViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PagingUiModel.UserItem -> TYPE_ITEM
            else -> TYPE_SEPARATOR
        }
    }

    inner class ItemViewHolder(
        private val binding: FragmentPagingTextItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GithubUser.ItemsItem?, viewModel: PagingViewModel) {
            binding.item = item
            binding.viewModel = viewModel
            binding.root.setOnClickListener {
                callback(item)
            }
        }
    }

    inner class SeparatorViewHolder(
        private val binding: FragmentPagingSeparatorItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel?.let {
            when (it) {
                is PagingUiModel.UserItem -> (viewHolder as ItemViewHolder).bind(
                    it.items, viewModel
                )
                else -> (viewHolder as SeparatorViewHolder)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            ItemViewHolder(
                FragmentPagingTextItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else {
            SeparatorViewHolder(
                FragmentPagingSeparatorItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_SEPARATOR = 1

        private val diffCallback = object : DiffUtil.ItemCallback<PagingUiModel>() {
            override fun areItemsTheSame(oldItem: PagingUiModel, newItem: PagingUiModel): Boolean {
                return (oldItem is PagingUiModel.UserItem && newItem is PagingUiModel.UserItem && oldItem.items.id == newItem.items.id) ||
                (oldItem is PagingUiModel.SeparatorItem && newItem is PagingUiModel.SeparatorItem &&
                        oldItem.item == newItem.item)
            }

            override fun areContentsTheSame(
                oldItem: PagingUiModel, newItem: PagingUiModel
            ): Boolean = oldItem == newItem
        }
    }
}