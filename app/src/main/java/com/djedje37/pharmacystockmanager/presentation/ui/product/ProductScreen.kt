package com.djedje37.pharmacystockmanager.presentation.ui.product

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.dp
import com.djedje37.pharmacystockmanager.presentation.ui.addproduct.AddProductDialog
import com.djedje37.pharmacystockmanager.presentation.ui.addproduct.NewProductInput

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductScreen(
    uiState: ProductUiState,
    onProductClick: (Long) -> Unit,
    onAddClick: () -> Unit,
    onAddDismiss: () -> Unit,
    onAddConfirm: (NewProductInput) -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier,
        floatingActionButton = { FloatingActionButton(onClick = onAddClick) { Text("+") } },
    ) {
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
                    Text(
                        product.name,
                        modifier = Modifier
                            .clickable { onProductClick(product.id) }
                            .padding(16.dp))
                }

            }
        }
   }
    if (uiState.addDialog.isVisible) {
        AddProductDialog(uiState.addDialog, onAddDismiss, onAddConfirm)
    }


}

