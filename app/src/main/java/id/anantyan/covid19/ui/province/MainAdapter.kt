package id.anantyan.covid19.ui.province

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.anantyan.covid19.databinding.ListItemMainBinding
import id.anantyan.covid19.model.ListDataItem
import javax.inject.Inject

class MainAdapter @Inject constructor() : ListAdapter<ListDataItem, RecyclerView.ViewHolder>(
    diffUtilCallback
), MainAdapterHelper {

    private var _onClick: ((Int, ListDataItem) -> Unit)? = null

    inner class ViewHolder(val binding: ListItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                _onClick?.let {
                    it(adapterPosition, getItem(adapterPosition))
                }
            }
        }

        fun bind(item: ListDataItem) {
            binding.txtProv.text = item.key
            binding.txtCount.text = String.format("%.2f %%", item.docCount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ListItemMainBinding.inflate(
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

    override fun init(): ListAdapter<ListDataItem, RecyclerView.ViewHolder> {
        return this
    }

    override fun differ(list: List<ListDataItem>) {
        submitList(list)
    }

    override fun onClick(listener: (Int, ListDataItem) -> Unit) {
        _onClick = listener
    }
}

val diffUtilCallback = object : DiffUtil.ItemCallback<ListDataItem>() {
    override fun areItemsTheSame(oldItem: ListDataItem, newItem: ListDataItem): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: ListDataItem, newItem: ListDataItem): Boolean {
        return oldItem == newItem
    }
}