package com.nirbhay.zeroq

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
class Cart(
    var productId:String,
    var productName:String,
    var price: Int
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}