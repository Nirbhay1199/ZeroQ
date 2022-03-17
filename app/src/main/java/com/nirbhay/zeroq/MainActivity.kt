package com.nirbhay.zeroq

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.nirbhay.zeroq.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object{
        lateinit var viewModel: ItemViewModel
    }
    private var text:String = ""
    private var scanData: String = ""
    private val allItems = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ItemViewModel::class.java]

        defaultItems()

        val searchProduct = SearchFragment()

        binding.search.setOnClickListener {
                binding.search.visibility = View.GONE
                binding.scan.visibility = View.GONE
                binding.details.visibility = View.GONE
                binding.cart.visibility = View.GONE
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment2, searchProduct)
                    addToBackStack(null)
                    commit()
                }
        }

        binding.scan.setOnClickListener {
            onButtonClick()
        }

        val productScanned = ProductsScanned()

        binding.details.setOnClickListener {
            binding.search.visibility = View.GONE
            binding.scan.visibility = View.GONE
            binding.details.visibility = View.GONE
            binding.cart.visibility = View.GONE
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, productScanned)
                addToBackStack(null)
                commit()
            }
        }

        val cart = CartFragment()

        binding.cart.setOnClickListener {
            binding.search.visibility = View.GONE
            binding.scan.visibility = View.GONE
            binding.details.visibility = View.GONE
            binding.cart.visibility = View.GONE
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment3, cart)
                addToBackStack(null)
                commit()
            }
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        binding.search.visibility = View.VISIBLE
        binding.scan.visibility = View.VISIBLE
        binding.details.visibility = View.VISIBLE
        binding.cart.visibility = View.VISIBLE
    }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Log.e("tag", "Cancelled")
        } else {

            scanData= result.contents

            showDialog()
        }
    }


    private fun onButtonClick() {
        barcodeLauncher.launch(ScanOptions())
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
        options.setPrompt("Scan a barcode")
        options.setOrientationLocked(false)
        options.setCameraId(0) // Use a specific camera of the device
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        barcodeLauncher.launch(options)
    }

    private fun showDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Title")


        val input = EditText(this)

        input.hint = "Product Name"
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)


        builder.setPositiveButton("OK") { _, _ ->
            val price = 100
            text = input.text.toString()

            val item = Item(scanData,text,price)
            viewModel.insertNote(item)
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.setCancelable(false)

        builder.show()
    }

    private fun defaultItems(){

        val item5 = Item("235425","LED TV", 59000)
        val item6 = Item("278965","Refrigerator", 19990)
        val item7 = Item("223651","Air Conditioner", 48999)
        val item8 = Item("215878","Laptop", 38998)

        if (allItems.contains(item5)){
            viewModel.deleteNote(item5)
        }
        if (allItems.contains(item6)){
            viewModel.deleteNote(item6)
        }
        if (allItems.contains(item7)){
            viewModel.deleteNote(item7)
        }
        if (allItems.contains(item8)){
            viewModel.deleteNote(item8)
        }

        viewModel.insertNote(item5)
        viewModel.insertNote(item6)
        viewModel.insertNote(item7)
        viewModel.insertNote(item8)
    }

}