package com.djedje37.pharmacystockmanager.domain.model

import java.time.LocalDate

data class Batch(
    val id : Long,
    val productId: Long,
    val quantity: Int,
    val expirationDate : LocalDate,
    val receivedDate: LocalDate,
    val purchasePrice: Double) {
    fun isExpired(today: LocalDate): Boolean = expirationDate.isBefore(today)

}
