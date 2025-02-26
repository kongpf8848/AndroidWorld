package com.github.kongpf8848.androidworld.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.model.ColorItem

class ColorAdapter(val ctx: Context,val list:List<ColorItem>): RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColorAdapter.ColorViewHolder {
        return ColorViewHolder(LayoutInflater.from(ctx).inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ColorAdapter.ColorViewHolder, position: Int) {
        val data=list[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.tv_title).setBackgroundColor(holder.itemView.resources.getColor(data.color))
            findViewById<TextView>(R.id.tv_title).text = String.format("color%d",position)
        }
    }

    class ColorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

}