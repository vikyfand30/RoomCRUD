package com.example.tooltrackapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tooltrackapp.model.Tools


class ToolsAdapter(
    private val listItems: ArrayList<Tools>,
    private val listener: ToolsListener
) : RecyclerView.Adapter<ToolsAdapter.ToolsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ToolsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ToolsViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewItem.text = item.item
        holder.textViewQuantity.text = item.qty
        holder.textViewBorrowed.text = item.get
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class ToolsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewItem = itemView.findViewById<TextView>(R.id.tv_name)
        var textViewQuantity = itemView.findViewById<TextView>(R.id.tv_qty)
        var textViewBorrowed = itemView.findViewById<TextView>(R.id.tv_borrowed)
    }

    interface ToolsListener{
        fun OnItemClicked(tools : Tools)
    }
}