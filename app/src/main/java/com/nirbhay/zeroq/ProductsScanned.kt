package com.nirbhay.zeroq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsScanned : Fragment(),INotesRVAdapter {


    private lateinit var itemAdapter: ItemRVAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products__scanned, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        itemAdapter = ItemRVAdapter(requireContext(),this)
        recyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
        }
        MainActivity.viewModel.allItems.observe(viewLifecycleOwner) { list ->
            list?.let {
                itemAdapter.updateList(it)
            }
        }

    }

    override fun onItemClicked(item: Item) {
        MainActivity.viewModel.deleteNote(item)
    }

    override fun onItemAddToCart(item: Item) {
        val cart = Cart(item.productId,item.productName,item.price)
        MainActivity.viewModel.insertCart(cart)
    }
}