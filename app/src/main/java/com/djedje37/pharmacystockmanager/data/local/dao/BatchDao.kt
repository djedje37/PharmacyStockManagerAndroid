package com.djedje37.pharmacystockmanager.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import com.djedje37.pharmacystockmanager.data.local.entity.BatchEntity

@Dao
interface BatchDao {

    @Insert
    suspend fun insertAll(batches: List<BatchEntity>)
}