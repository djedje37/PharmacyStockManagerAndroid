package com.djedje37.pharmacystockmanager.domain.model

import java.time.LocalDate

data class StockMovement(
    val id: Long,
    val productId: Long,
    val type: MovementType,
    val quantity: Int,
    val date: LocalDate
)
