package com.djedje37.pharmacystockmanager.domain.model

import java.time.LocalDate

data class Product(
    val id: Long,
    val cip: String,
    val name: String,
    val category: String,
    val publicPrice: Double,
    val reimbursementBase: Double,
    val vatRate: Int,
    val alertThreshold: Int,
    val geoCode: String,
    val batches: List<Batch> = emptyList(),
) {
    val currentStock: Int get() = batches.sumOf { it.quantity }
    val isLowStock: Boolean get() = currentStock <= alertThreshold
}