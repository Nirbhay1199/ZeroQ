package com.nirbhay.zeroq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment(), ICartAdapter{

    private lateinit var cartAdapter: CartAdapter

    private val cartItems = ArrayList<Cart>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerView3)
        cartAdapter = CartAdapter(requireContext(),this)
        recyclerview.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(context)
        }
        MainActivity.viewModel.cartItems.observe(viewLifecycleOwner){list->
            list?.let {
                cartAdapter.updateList(it)
            }
        }
    }

    override fun onCartClicked(cart: Cart) {
        MainActivity.viewModel.deleteCart(cart)
    }
}