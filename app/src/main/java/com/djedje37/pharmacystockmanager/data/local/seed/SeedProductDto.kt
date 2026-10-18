package com.djedje37.pharmacystockmanager.data.local.seed

import kotlinx.serialization.Serializable

// contain a product from json
@Serializable
data class SeedProductDto(
    val cip: String,
    val name: String,
    val category: String = "",
    val publicPrice: Double = 0.0,
    val currentStock: Int = 0,
    val alertThreshold: Int = 0,
    val lastSaleDate: String? = null,
    val knownExpirationDate: String? = null,
    val reimbursementBase: Double = 0.0,
    val vatRate: Int = 0,
    val purchasePrice: Double = 0.0,
    val geoCode: String = "",
)
