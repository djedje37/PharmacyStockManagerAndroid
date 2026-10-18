package com.djedje37.pharmacystockmanager.presentation.ui.addproduct

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.djedje37.pharmacystockmanager.domain.usecase.AddProductError
import com.djedje37.pharmacystockmanager.presentation.ui.product.AddDialogState

@Composable
fun AddProductDialog(
    state: AddDialogState,
    onDismiss: () -> Unit,
    onConfirm: (NewProductInput) -> Unit) {

    var cip by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    var publicPrice by rememberSaveable { mutableStateOf("") }
    var purchasePrice by rememberSaveable { mutableStateOf("") }
    var stock by rememberSaveable { mutableStateOf("") }
    var threshold by rememberSaveable { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nouveau Produit") },
        text = {
            Column(
                Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Field("CIP", cip) { cip = it }
                Field("Nom", name) { name = it }
                Field("Catégorie", category) { category = it }
                Field("Prix public", publicPrice, KeyboardType.Decimal) { publicPrice = it }
                Field("Prix d'achat", purchasePrice, KeyboardType.Decimal) { purchasePrice = it }
                Field("Stock", stock, KeyboardType.Number) { stock = it }
                Field("Seuil d'alerte", threshold, KeyboardType.Number) { threshold = it }
                state.error?.let  { Text(it.message(), color = MaterialTheme.colorScheme.error) }

            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(NewProductInput(cip, name, category, publicPrice, purchasePrice, stock, threshold)) }) {
                Text("Ajouter")
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Annuler") } },
    )
}


@Composable
private fun Field(label: String, value: String, type: KeyboardType = KeyboardType.Text, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = value, onValueChange = onChange, label = { Text(label) }, singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = type),
    )
}

private fun AddProductError.message() = when (this) {
    AddProductError.InvalidNumber -> "Un nombre est invalide."
    AddProductError.BlankCip -> "Le CIP est obligatoire."
    AddProductError.BlankName -> "Le nom est obligatoire."
    AddProductError.NegativeValue -> "Les valeurs ne peuvent pas être négatives."
    AddProductError.DuplicateCip -> "Ce CIP existe déjà."
}