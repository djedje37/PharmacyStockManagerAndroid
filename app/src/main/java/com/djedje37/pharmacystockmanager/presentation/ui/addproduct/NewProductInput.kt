package com.djedje37.pharmacystockmanager.presentation.ui.addproduct

import com.djedje37.pharmacystockmanager.domain.model.Product

data class NewProductInput(val cip: String, val name: String, val category: String,
    val publicPrice: String, val purchasePrice: String, val currentStock: String,
    val alertThreshold: String,
    ) {
    fun toProductOrNull(): Product? {
        val pub = publicPrice.toDecimalOrNull() ?: 0.0
        val buy = purchasePrice.toDecimalOrNull() ?: 0.0
        val stock = currentStock.trim().toIntOrNull() ?: 0
        val threshold = alertThreshold.trim().toIntOrNull() ?: 0

        return Product( id = 0, cip = cip, name = name, category = category,
            publicPrice = pub, /*purchasePrice = buy ,*/ reimbursementBase = 0.0, vatRate = 0,
            /*currentStock = stock,*/ alertThreshold = threshold,
            /*lastSaleDate = null, knownExpirationDate = null, */geoCode = "",)





    }
}
private fun String.toDecimalOrNull() = trim().replace(',', '.').toDoubleOrNull()