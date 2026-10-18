package com.djedje37.pharmacystockmanager.presentation.ui.product

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.djedje37.pharmacystockmanager.domain.usecase.ObserveProductsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.djedje37.pharmacystockmanager.PharmacyStockManager
import com.djedje37.pharmacystockmanager.domain.usecase.AddProductError
import com.djedje37.pharmacystockmanager.domain.usecase.AddProductResult
import com.djedje37.pharmacystockmanager.domain.usecase.AddProductUseCase
import com.djedje37.pharmacystockmanager.presentation.ui.addproduct.NewProductInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    observeProducts: ObserveProductsUseCase,
    private val addProduct: AddProductUseCase
): ViewModel() {

    private val addDialog = MutableStateFlow(AddDialogState())

    val uiState : StateFlow<ProductUiState> =
        combine( observeProducts(), addDialog) {
            products, dialogState ->
            ProductUiState(products = products, isLoading = false, addDialog = dialogState)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProductUiState(),
        )

    fun onAddClick() { addDialog.value = AddDialogState(isVisible = true)
    }

    fun onAddDismiss() {addDialog.value = AddDialogState(isVisible = false)}

    fun onAddConfirm(input: NewProductInput) {
        val product = input.toProductOrNull()
        if (product == null) {
            addDialog.update { it.copy(error = AddProductError.InvalidNumber) }
            return
        }

        viewModelScope.launch {
            when (val result = addProduct(product)) {
                AddProductResult.Success -> addDialog.value = AddDialogState()
                is AddProductResult.Failure -> addDialog.update { it.copy(error = result.error) }
            }

        }
    }

    companion object {
        val factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as PharmacyStockManager
                ProductViewModel(app.container.observeProductsUseCase, app.container.addProductUseCase)
            }
        }
    }
}