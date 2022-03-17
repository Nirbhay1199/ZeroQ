package com.nirbhay.zeroq

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao,private val cartDao: CartDao) {
    val allItems: LiveData<List<Item>> = itemDao.getAllItems()

    val cartItems: LiveData<List<Cart>> = cartDao.getAllItems()

    suspend fun insertCart(cart: Cart){
        cartDao.insert(cart)
    }

    suspend fun deleteCart(cart: Cart){
        cartDao.delete(cart)
    }

    suspend fun insert(item: Item){
        itemDao.insert(item)
    }

    suspend fun delete(item: Item){
        itemDao.delete(item)
    }

    fun searchDatabase(searchQuery: String): Flow<List<Item>>{
        return itemDao.searchDatabase(searchQuery)
    }
}