package com.djedje37.pharmacystockmanager.data.local.entity

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import java.util.Date


// Batch Table
@Entity(
    tableName = "batches",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["productId"])]
)
data class BatchEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: Long, // Foreign key
    val expirationDate: String?,
    val receivedDate: String?,
    val purchasePrice: Double,
    val quantity: Int,


)