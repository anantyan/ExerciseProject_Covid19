package id.anantyan.covid19.ui.score

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.anantyan.covid19.databinding.ListItemMainBinding
import id.anantyan.covid19.databinding.ListItemScoreBinding
import id.anantyan.covid19.model.DataItem
import id.anantyan.covid19.model.ListDataItem
import id.anantyan.covid19.ui.province.MainAdapter
import javax.inject.Inject

class ScoreAdapter @Inject constructor() : ListAdapter<DataItem, RecyclerView.ViewHolder>(
    diffUtilCallback
), ScoreAdapterHelper {

    inner class ViewHolder(private val binding: ListItemScoreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem) {
            binding.txtKota.text = item.kota
            binding.txtStatus.text = item.hasil
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ListItemScoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        val item = getItem(position)
        holder.bind(item)
    }

    override fun init(): ListAdapter<DataItem, RecyclerView.ViewHolder> {
        return this
    }

    override fun differ(list: List<DataItem>) {
        submitList(list)
    }
}

val diffUtilCallback = object : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.kodeProv == newItem.kodeProv
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}