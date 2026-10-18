package com.djedje37.pharmacystockmanager.presentation.ui.product

import com.djedje37.pharmacystockmanager.domain.model.Product
import com.djedje37.pharmacystockmanager.domain.usecase.AddProductError

data class AddDialogState(val isVisible: Boolean = false, val error: AddProductError? = null)

data class ProductUiState (
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = true,
    val addDialog: AddDialogState = AddDialogState(),
)