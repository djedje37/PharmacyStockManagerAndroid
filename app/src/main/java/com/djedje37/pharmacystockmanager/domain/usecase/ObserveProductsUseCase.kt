package com.djedje37.pharmacystockmanager.domain.usecase

import com.djedje37.pharmacystockmanager.domain.model.Product
import com.djedje37.pharmacystockmanager.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveProductsUseCase(private val repository: ProductRepository) {

    operator fun invoke(id: Long): Flow<Product?> = repository.observeProduct(id)

    operator fun invoke(query: String =""): Flow<List<Product>> =
        repository.observeProducts().map { products ->
            products.filter { it.name.contains(query.trim(), ignoreCase = true) }
                .sortedBy { it.name.lowercase() }
        }
}