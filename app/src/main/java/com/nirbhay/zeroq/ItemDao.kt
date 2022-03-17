package com.nirbhay.zeroq

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("Select * from product_table order by id ASC")
    fun getAllItems(): LiveData<List<Item>>

    @Query("SELECT * FROM product_table WHERE productId LIKE '%'||:searchQuery||'%' OR productName LIKE '%'||:searchQuery||'%'")
    fun searchDatabase(searchQuery: String): Flow<List<Item>>
}