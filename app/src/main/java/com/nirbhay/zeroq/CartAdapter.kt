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

class CartAdapter(private val context: Context, private val listener:ICartAdapter): RecyclerView.Adapter<CartAdapter.ItemViewHolder>() {


        private val allItems = ArrayList<Cart>()


        inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val textView: TextView? = itemView.findViewById(R.id.itemId2)
            val textView2: TextView? = itemView.findViewById(R.id.itemName2)
            val textView3: TextView? = itemView.findViewById(R.id.itemPrice2)
            val deleteBtn: ImageView? = itemView.findViewById(R.id.delete2)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewHolder = ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cartview,parent,false))
        viewHolder.deleteBtn?.setOnClickListener {
            listener.onCartClicked(allItems[viewHolder.adapterPosition])
            Toast.makeText(context,"Product Deleted",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val cartItem = allItems[position]
        holder.textView?.text = cartItem.productId
        holder.textView2?.text = cartItem.productName
        holder.textView3?.text = cartItem.price.toString()
    }

    override fun getItemCount(): Int {
        return allItems.size
    }

    fun updateList(list: List<Cart>){
        allItems.clear();
        allItems.addAll(list)
        notifyDataSetChanged()
    }

}

interface  ICartAdapter {
    fun onCartClicked(cart: Cart)
}

