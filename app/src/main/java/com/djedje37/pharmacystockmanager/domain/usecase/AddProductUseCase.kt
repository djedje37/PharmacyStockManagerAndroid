package com.djedje37.pharmacystockmanager.domain.usecase

import com.djedje37.pharmacystockmanager.domain.model.Product
import com.djedje37.pharmacystockmanager.domain.repository.ProductRepository

class AddProductUseCase(private val repository: ProductRepository) {

    suspend operator fun invoke(product: Product) : AddProductResult {
        val product = product.copy(cip = product.cip.trim(), name = product.name.trim() )
        if (product.cip.isBlank()) return AddProductResult.Failure(AddProductError.BlankCip)
        if (product.name.isBlank()) return AddProductResult.Failure(AddProductError.BlankName)

        if (product.currentStock < 0 || product.alertThreshold < 0 || product.publicPrice < 0 || product.purchasePrice < 0)
            return AddProductResult.Failure(AddProductError.NegativeValue)

        if(repository.existsByCip(product.cip)) {
            return AddProductResult.Failure(AddProductError.DuplicateCip)
        }
        repository.addProduct(product)

        return AddProductResult.Success


    }
}