package com.djedje37.pharmacystockmanager.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.djedje37.pharmacystockmanager.data.local.dao.ProductDao
import com.djedje37.pharmacystockmanager.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1,  exportSchema = false)
abstract class PharmacyDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}