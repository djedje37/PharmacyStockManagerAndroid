package com.djedje37.pharmacystockmanager.domain.repository;

import com.djedje37.pharmacystockmanager.domain.model.Product;

import kotlinx.coroutines.flow.Flow;

interface ProductRepository {
    fun observeProducts(): Flow<List<Product>>

    // Add
    suspend fun existsByCip(cip: String): Boolean
    suspend fun addProduct(product: Product)
}
