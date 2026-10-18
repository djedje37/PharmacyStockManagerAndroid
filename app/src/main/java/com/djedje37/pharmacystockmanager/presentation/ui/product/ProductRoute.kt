package com.djedje37.pharmacystockmanager.presentation.ui.product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun ProductRoute(viewModel: ProductViewModel = viewModel(factory = ProductViewModel.factory )) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    ProductScreen(uiState = state)
}