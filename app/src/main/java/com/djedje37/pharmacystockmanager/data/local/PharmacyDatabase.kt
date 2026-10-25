package com.djedje37.pharmacystockmanager.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.djedje37.pharmacystockmanager.data.local.dao.BatchDao
import com.djedje37.pharmacystockmanager.data.local.dao.ProductDao
import com.djedje37.pharmacystockmanager.data.local.dao.StockMovementDao
import com.djedje37.pharmacystockmanager.data.local.entity.BatchEntity
import com.djedje37.pharmacystockmanager.data.local.entity.ProductEntity
import com.djedje37.pharmacystockmanager.data.local.entity.StockMovementEntity

@Database(entities = [ProductEntity::class, BatchEntity::class, StockMovementEntity::class], version = 1,  exportSchema = false)
abstract class PharmacyDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun batchDao(): BatchDao
    abstract fun stockMovementDao(): StockMovementDao
}