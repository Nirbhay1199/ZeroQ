package com.nirbhay.zeroq

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Item(
    var productId:String,
    var productName:String,
    var price: Int

    )
{
    @PrimaryKey(autoGenerate = true) var id = 0
}