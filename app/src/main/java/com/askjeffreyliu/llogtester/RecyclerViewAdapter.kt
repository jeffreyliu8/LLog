package com.askjeffreyliu.llogtester

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.askjeffreyliu.llog.MobileLog
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerViewAdapter(private var items: List<MobileLog>?, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        if (items == null) {
            return 0
        }
        return items!!.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.myTextView.text = items!![position].id.toString() + " " + items!![position].message
    }

    fun setList(log: List<MobileLog>?) {
        items = log
        notifyDataSetChanged()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val myTextView: TextView = view.myTextView
}