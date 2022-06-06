package jp.co.yumemi.android.codeCheck.di.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.co.yumemi.android.codeCheck.R
import jp.co.yumemi.android.codeCheck.di.data.GitItem

class CustomAdapter(
    private val itemClickListener: OnItemClickListener,
) : ListAdapter<GitItem, CustomAdapter.ViewHolder>(diff_util) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnItemClickListener {
        fun itemClick(item: GitItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val textView = holder.itemView.findViewById<TextView>(R.id.repositoryNameView)
        textView.text = item.name

        holder.itemView.setOnClickListener {
            itemClickListener.itemClick(item)
        }
    }
}

val diff_util = object : DiffUtil.ItemCallback<GitItem>() {
    override fun areItemsTheSame(oldItem: GitItem, newItem: GitItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: GitItem, newItem: GitItem): Boolean {
        return oldItem == newItem
    }
}