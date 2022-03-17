package com.nirbhay.zeroq

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Cart)

    @Delete
    suspend fun delete(item: Cart)

    @Query("Select * from cart_table order by id ASC")
    fun getAllItems(): LiveData<List<Cart>>

}