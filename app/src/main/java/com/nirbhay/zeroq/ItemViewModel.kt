package com.nirbhay.zeroq

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel (application: Application) : AndroidViewModel(application){

    val allItems: LiveData<List<Item>>
    val cartItems: LiveData<List<Cart>>

    private val repository: ItemRepository
    init {
        val dao = ItemDB.getDatabase(application).getItemDao()
        val cartDao = ItemDB.getDatabase(application).getCartDao()
        repository = ItemRepository(dao,cartDao)
        allItems= repository.allItems
        cartItems = repository.cartItems
    }

    fun deleteNote(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }

    fun insertNote(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Item>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    fun deleteCart(item: Cart) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCart(item)
    }

    fun insertCart(item: Cart) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertCart(item)
    }
}