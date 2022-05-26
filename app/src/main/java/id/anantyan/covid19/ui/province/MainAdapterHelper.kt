package id.anantyan.covid19.ui.province

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.anantyan.covid19.model.ListDataItem

interface MainAdapterHelper {
    fun init(): ListAdapter<ListDataItem, RecyclerView.ViewHolder>
    fun differ(list: List<ListDataItem>)
    fun onClick(listener: (Int, ListDataItem) -> Unit)
}