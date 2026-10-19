package com.djedje37.pharmacystockmanager.presentation.ui.product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun ProductRoute(
    onProductClick: (Long) -> Unit,
    viewModel: ProductViewModel = viewModel(factory = ProductViewModel.factory )
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ProductScreen(
        onProductClick = { id -> onProductClick(id) },
        uiState = state,
        onAddClick = viewModel::onAddClick,
        onAddDismiss = viewModel::onAddDismiss,
        onAddConfirm = viewModel::onAddConfirm,
    )
}