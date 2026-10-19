package com.djedje37.pharmacystockmanager.domain.usecase

import com.djedje37.pharmacystockmanager.domain.model.Product
import com.djedje37.pharmacystockmanager.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ObserveProductUseCase(private val repository: ProductRepository) {

    operator fun invoke(id: Long): Flow<Product?> = repository.observeProduct(id)

}