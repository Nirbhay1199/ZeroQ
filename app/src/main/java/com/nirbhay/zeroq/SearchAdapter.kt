package com.nirbhay.zeroq

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class SearchAdapter(private val context: Context): RecyclerView.Adapter<SearchAdapter.ItemViewHolder>(){

    private val allItems = ArrayList<Item>()

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView? = itemView.findViewById(R.id.itemId2)
        val textView2: TextView? = itemView.findViewById(R.id.itemName2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.listview_search,parent, false))

        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = allItems[position]
        holder.textView?.text = currentItem.productId
        holder.textView2?.text = currentItem.productName
    }


    override fun getItemCount(): Int {
        return allItems.size
    }

    fun updateList(newList: List<Item>){
        allItems.clear()
        allItems.addAll(newList)
        notifyDataSetChanged()
    }

}

