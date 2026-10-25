package com.djedje37.pharmacystockmanager.data.local.seed

import androidx.room3.util.splitToIntList
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.random.Random


data class BatchPlan(
    val quantity: Int,
    val receiveDate: LocalDate,
    val expirationDate: LocalDate,
    val purchasePrice: Double
)

private enum class ExpirationProfile {
    EXPIRED, SOON, NORMAL;
    companion object {
        fun random(random: Random): ExpirationProfile {
            val n = random.nextInt() // 0..9, => 30 % / 20 % / 50 %
            return when {
                n < 3 -> EXPIRED
                n < 5 -> SOON
                else -> NORMAL
            }
        }
    }
}
class SeedBatchGenerator (private val random: Random = Random.Default) {
    fun generatePlans(
        stock: Int,
        purchasePrice: Double,
        knownExpirationDate: LocalDate?,
        today: LocalDate
    ) : List<BatchPlan> {

        if (stock <= 0) return emptyList()

        val quantities = splitStock(stock, batchCount(stock))

        return quantities.mapIndexed { index, quantity ->
            val expiration = if (index == 0 && knownExpirationDate != null) {
                knownExpirationDate
            } else {
                randomExpiration(today)
            }

            BatchPlan(
                quantity = quantity,
                receiveDate = receiveDate(today, expiration),
                expirationDate = expiration,
                purchasePrice = purchasePrice
            )
        }
    }

    private fun batchCount(stock: Int) : Int = when(stock) {
        in 1..3 -> 1
        in 4..15 -> random.nextInt(1, 3)    // 1 or 2
        else -> random.nextInt(3, 5)                // 3 or 4
    }
    private fun splitStock(stock: Int, batchCount: Int) : List<Int> {
        var remaining = stock
        return List(batchCount) { index ->
            if(index == batchCount -1) {
                remaining
            } else {
                val share = random.nextInt(1, maxOf(1, remaining/2) + 1)
                remaining -= share
                share
            }
        }
    }

    private fun receiveDate(today: LocalDate, expirationDate: LocalDate) : LocalDate {
        val daysToExpiry = ChronoUnit.DAYS.between(today, expirationDate).toInt()
        val maxDaysAgo = daysToExpiry.coerceIn(30, 360)
        val received = today.minusDays(random.nextInt(10, maxDaysAgo +1).toLong())
        val latestAllowed = expirationDate.minusDays(30)
        return if(received.isAfter(latestAllowed)) latestAllowed else received
    }

    private fun randomExpiration(today: LocalDate): LocalDate =
        when(ExpirationProfile.random(random)) {
            ExpirationProfile.EXPIRED -> today.minusDays(random.nextInt(5, 91).toLong()) // 5...90
            ExpirationProfile.SOON -> today.plusDays(random.nextInt(1, 30).toLong()) // 1...29
            ExpirationProfile.NORMAL -> today.plusDays(random.nextInt(90, 901).toLong()) // 90...900
        }
}