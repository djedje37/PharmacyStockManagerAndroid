package com.djedje37.pharmacystockmanager.presentation.ui.product

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

class ProductViewModel(observeProducts: ObserveProductsUseCase): ViewModel() {

    val uiState : StateFlow<ProductUiState> = observeProducts()
        .map { products -> ProductUiState(products = products, isLoading = false) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProductUiState(),
        )

    companion object {
        val factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as PharmacyStockManager
                ProductViewModel(app.container.observeProductsUseCase)
            }
        }
    }
}