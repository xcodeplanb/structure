package com.example.structure.ui.whether

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.structure.data.model.Weather
import com.example.structure.databinding.WeatherListItemBinding
import com.example.structure.databinding.WeatherListItemHeaderBinding

class WeatherAdapter :
    ListAdapter<Weather.DailyItem, RecyclerView.ViewHolder>(TaskDiffCallback()) {
    private val HEADER_VIEW = 0
    private val ITEM_VIEW = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_VIEW -> {
                val binding = WeatherListItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(binding)
            }
            else -> {
                val binding = WeatherListItemBinding.inflate(
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

    inner class HeaderViewHolder(private val binding: WeatherListItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Weather.DailyItem) {
            binding.item = item
        }
    }

    inner class ItemViewHolder(private val binding: WeatherListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Weather.DailyItem) {
            binding.item = item
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Weather.DailyItem>() {
        override fun areItemsTheSame(
            oldItem: Weather.DailyItem, newItem: Weather.DailyItem
        ): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(
            oldItem: Weather.DailyItem, newItem: Weather.DailyItem
        ): Boolean {
            return oldItem.dt == newItem.dt
        }
    }
}