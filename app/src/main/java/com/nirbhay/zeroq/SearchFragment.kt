package com.nirbhay.zeroq

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchFragment : Fragment(){

    private lateinit var searchAdapter: SearchAdapter

    private val allItems = ArrayList<Item>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(R.id.search)
        editText.requestFocus()
        editText.addTextChangedListener(textWatcher)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView2)
        searchAdapter = SearchAdapter(requireContext())
        recyclerView?.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(context)
        }
        MainActivity.viewModel.allItems.observe(viewLifecycleOwner){list ->
            list?.let {
                searchAdapter.updateList(it)
            }
        }

    }

    private val textWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
        override fun afterTextChanged(s: Editable?) {
            val query = s.toString()
            if(query.isNotEmpty()){
                MainActivity.viewModel.searchDatabase(query).observe(viewLifecycleOwner) { list ->
                    list.let {
                        searchAdapter.updateList(it)
                    }
                }
            }
        }
    }


}

