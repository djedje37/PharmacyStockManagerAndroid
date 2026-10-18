package com.djedje37.pharmacystockmanager.data.local.entity

import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey

// Product Table
@Entity(
    tableName = "products",
    indices = [Index(value = ["cip"], unique = true)],
)
data class ProductEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cip: String,
    val name: String,
    val category: String,
    val publicPrice: Double,
    val purchasePrice: Double,
    val reimbursementBase: Double,
    val vatRate: Int,
    val currentStock: Int,
    val alertThreshold: Int,
    val lastSaleDate: String?,          // "2026-05-22", format ISO
    val knownExpirationDate: String?,
    val geoCode: String,
)