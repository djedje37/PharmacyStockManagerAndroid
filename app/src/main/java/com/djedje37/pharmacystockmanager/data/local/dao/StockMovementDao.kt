package com.djedje37.pharmacystockmanager.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import com.djedje37.pharmacystockmanager.data.local.entity.StockMovementEntity

@Dao
interface StockMovementDao {

    @Insert
    suspend fun insert(stockMovement: StockMovementEntity)

    @Insert
    suspend fun insertAll(stockMovements: List<StockMovementEntity>)
}