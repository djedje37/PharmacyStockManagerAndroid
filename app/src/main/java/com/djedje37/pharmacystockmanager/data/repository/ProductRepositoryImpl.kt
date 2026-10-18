package com.djedje37.pharmacystockmanager.data.repository

import com.djedje37.pharmacystockmanager.data.local.dao.ProductDao
import com.djedje37.pharmacystockmanager.data.mapper.toDomain
import com.djedje37.pharmacystockmanager.domain.model.Product
import com.djedje37.pharmacystockmanager.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(private val dao: ProductDao) : ProductRepository {
    override fun observeProducts(): Flow<List<Product>> {
        return dao.observeAll().map {
            entities -> entities.map {
                it.toDomain()
        }
        }
    }
}