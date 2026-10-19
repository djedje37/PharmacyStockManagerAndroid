package com.djedje37.pharmacystockmanager.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.djedje37.pharmacystockmanager.PharmacyStockManager
import com.djedje37.pharmacystockmanager.domain.model.Product
import com.djedje37.pharmacystockmanager.domain.usecase.ObserveProductUseCase
import com.djedje37.pharmacystockmanager.presentation.ui.navigation.ProductDetail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
data class ProductDetailUiState(val product: Product? = null, val isLoading: Boolean = true)

class ProductDetailViewModel(
    savedStateHandle: SavedStateHandle,
    observeProduct: ObserveProductUseCase,
) : ViewModel() {

    private val productId = savedStateHandle.toRoute<ProductDetail>().productId

    val uiState: StateFlow<ProductDetailUiState> = observeProduct(productId)
        .map { ProductDetailUiState(product = it, isLoading = false) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ProductDetailUiState())

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as PharmacyStockManager
                ProductDetailViewModel(createSavedStateHandle(), app.container.observeProductUseCase)
            }
        }
    }
}