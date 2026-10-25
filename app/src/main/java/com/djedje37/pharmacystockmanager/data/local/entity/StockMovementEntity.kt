package com.djedje37.pharmacystockmanager.data.local.entity

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import com.djedje37.pharmacystockmanager.domain.model.MovementType


// Stock Movement Table
@Entity(
    tableName = "stockMovements",
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
data class StockMovementEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: Long, // Foreign key
    val type: MovementType, // "ENTRY" or "EXIT"
    val quantity: Int,
    val date: String?,

    )