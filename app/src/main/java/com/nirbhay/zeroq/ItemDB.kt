package com.nirbhay.zeroq

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class, Cart::class], version = 1, exportSchema = false)
abstract class ItemDB : RoomDatabase(){

    abstract fun getItemDao(): ItemDao

    abstract fun getCartDao(): CartDao

    companion object{


        private var INSTANCE: ItemDB? = null

        fun getDatabase(context: Context): ItemDB{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDB::class.java,
                    "product_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}