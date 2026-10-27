package com.djedje37.pharmacystockmanager.testing

import com.djedje37.pharmacystockmanager.domain.model.Product
import com.djedje37.pharmacystockmanager.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map


class FakeProductRepository (
    initialProducts: List<Product> = emptyList()
) : ProductRepository {
    private val products = MutableStateFlow(initialProducts)
    override fun observeProducts(): Flow<List<Product>> {
        return products
    }

    override fun observeProduct(id: Long): Flow<Product?> =
        products.map { list ->
            list.firstOrNull() {it.id == id}

        }


    override suspend fun existsByCip(cip: String): Boolean {
        return products.value.any { it.cip == cip}
    }

    override suspend fun addProduct(product: Product) {
        products.value += product
    }

}
