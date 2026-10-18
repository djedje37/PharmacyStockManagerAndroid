package com.djedje37.pharmacystockmanager.domain.model

import java.time.LocalDate

data class Product(
    val id: Long,
    val cip: String,
    val name: String,
    val category: String,
    val publicPrice: Double,
    val purchasePrice: Double,
    val reimbursementBase: Double,
    val vatRate: Int,
    val currentStock: Int,
    val alertThreshold: Int,
    val lastSaleDate: LocalDate?,
    val knownExpirationDate: LocalDate?,
    val geoCode: String,
) {
    val isLowStock: Boolean get() = currentStock <= alertThreshold
}