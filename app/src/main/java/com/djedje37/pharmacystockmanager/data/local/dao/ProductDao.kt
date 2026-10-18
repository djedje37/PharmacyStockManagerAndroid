package com.djedje37.pharmacystockmanager.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.djedje37.pharmacystockmanager.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {



    @Query("SELECT * FROM products")
    fun observeAll(): Flow<List<ProductEntity>>

    @Query("SELECT COUNT(*) FROM products")
    suspend fun count() : Int

    @Insert
    suspend fun insertAll(products: List<ProductEntity>)


}