package com.djedje37.pharmacystockmanager.presentation.ui.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.djedje37.pharmacystockmanager.Greeting
import com.djedje37.pharmacystockmanager.presentation.theme.PharmacyStockManagerTheme

@Composable
fun ProductScreen(uiState: ProductUiState, modifier: Modifier = Modifier) {

    when {
        uiState.isLoading -> Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        uiState.products.isEmpty() -> Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Aucun Produt")
        }
        else -> LazyColumn(modifier.fillMaxSize()) {
            items(
                items = uiState.products,
                key = {it.id}
            ) { product ->
                Text(product.name, modifier = Modifier.padding(16.dp))
            }

        }
    }
}

