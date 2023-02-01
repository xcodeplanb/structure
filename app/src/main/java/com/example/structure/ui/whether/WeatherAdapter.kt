package com.example.structure.ui.whether

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.structure.data.vo.WeatherVo
import com.example.structure.databinding.ListItemBinding
import com.example.structure.databinding.ListItemHeaderBinding

class WeatherAdapter :
    ListAdapter<WeatherVo.DailyItem, RecyclerView.ViewHolder>(TaskDiffCallback()) {
    private val HEADER_VIEW = 0
    private val ITEM_VIEW = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_VIEW -> {
                val binding = ListItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(binding).also {
                    binding
                }
            }
            else -> {
                val binding = ListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ItemViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            HEADER_VIEW -> (holder as HeaderViewHolder).bind(getItem(position))
            ITEM_VIEW -> (holder as ItemViewHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isHeaderPositon) {
            HEADER_VIEW
        } else {
            ITEM_VIEW
        }
    }

    inner class HeaderViewHolder(private val binding: ListItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherVo.DailyItem) {
            binding.item = item
        }
    }

    inner class ItemViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherVo.DailyItem) {
            binding.item = item
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<WeatherVo.DailyItem>() {
        override fun areItemsTheSame(
            oldItem: WeatherVo.DailyItem, newItem: WeatherVo.DailyItem
        ): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(
            oldItem: WeatherVo.DailyItem, newItem: WeatherVo.DailyItem
        ): Boolean {
            return oldItem.dt == newItem.dt
        }
    }
}