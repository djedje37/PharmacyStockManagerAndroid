package com.djedje37.pharmacystockmanager.presentation.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProductDetailScreen(state: ProductDetailUiState, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextButton(onClick = onBack) { Text("← Retour") }
        val product = state.product
        when {
            state.isLoading -> CircularProgressIndicator()
            product == null -> Text("Produit introuvable")
            else -> {
                Text(product.name, style = MaterialTheme.typography.headlineSmall)
                DetailRow("CIP", product.cip)
                DetailRow("Catégorie", product.category)
                DetailRow("Prix public", product.publicPrice.toString())
                DetailRow("Prix d'achat", product.purchasePrice.toString())
                DetailRow("Stock", "${product.currentStock} (seuil ${product.alertThreshold})")
                if (product.isLowStock) Text("Stock bas", color = MaterialTheme.colorScheme.error)
                DetailRow("Dernière vente", product.lastSaleDate?.toString() ?: "—")
                DetailRow("Péremption connue", product.knownExpirationDate?.toString() ?: "—")
                DetailRow("Emplacement", product.geoCode)
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("$label :", fontWeight = FontWeight.Bold)
        Text(value)
    }
}