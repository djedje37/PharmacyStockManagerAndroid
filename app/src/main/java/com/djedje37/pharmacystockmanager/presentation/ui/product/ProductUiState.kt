package com.djedje37.pharmacystockmanager.presentation.ui.product

import com.djedje37.pharmacystockmanager.domain.model.Product

data class ProductUiState (
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = true,
)