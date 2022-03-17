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

class ItemRVAdapter (private val context: Context, private val listener:INotesRVAdapter): RecyclerView.Adapter<ItemRVAdapter.ItemViewHolder>(){

    private val allItems = ArrayList<Item>()


    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView? = itemView.findViewById(R.id.itemId)
        val textView2: TextView? = itemView.findViewById(R.id.itemName)
        val textView3: TextView? = itemView.findViewById(R.id.itemPrice)
        val addBtn: ImageView? = itemView.findViewById(R.id.add)
        val deleteBtn: ImageView? = itemView.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product,parent, false))
        viewHolder.deleteBtn?.setOnClickListener {
            listener.onItemClicked(allItems[viewHolder.adapterPosition])
            Toast.makeText(context,"Product Deleted",Toast.LENGTH_SHORT).show()
        }
        viewHolder.addBtn?.setOnClickListener {
            listener.onItemAddToCart(allItems[viewHolder.adapterPosition])
            Toast.makeText(context,"Product Added To Cart",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentNote = allItems[position]
        holder.textView?.text = currentNote.productId
        holder.textView2?.text = currentNote.productName
        holder.textView3?.text = currentNote.price.toString()

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

interface  INotesRVAdapter {
    fun onItemClicked(item: Item)
    fun onItemAddToCart(item: Item)
}