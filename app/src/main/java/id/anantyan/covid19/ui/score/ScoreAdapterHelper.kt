package id.anantyan.covid19.ui.score

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.anantyan.covid19.model.DataItem

interface ScoreAdapterHelper {
    fun init(): ListAdapter<DataItem, RecyclerView.ViewHolder>
    fun differ(list: List<DataItem>)
}