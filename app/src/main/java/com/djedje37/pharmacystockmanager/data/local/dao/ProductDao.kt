package com.djedje37.pharmacystockmanager.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Transaction
import com.djedje37.pharmacystockmanager.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE id = :id")
    fun observeById(id: Long): Flow<ProductEntity>

    @Query("SELECT * FROM products WHERE cip = :cip")
    fun countByCip(cip : String) : Int

    @Transaction
    @Query("SELECT * FROM products")
    fun observeAll(): Flow<List<ProductEntity>>

    @Query("SELECT COUNT(*) FROM products")
    suspend fun count() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity): Long
    @Insert
    suspend fun insertAll(products: List<ProductEntity>)
}