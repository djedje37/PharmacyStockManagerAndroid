package com.djedje37.pharmacystockmanager.batch

import com.djedje37.pharmacystockmanager.data.local.seed.SeedBatchGenerator
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class SeedBatchGeneratorTest {

    private val today = LocalDate.of(2026, 10, 24)


    @Test
    fun noStockGivesNoBatch() {
        val plans = SeedBatchGenerator(Random(1)).generatePlans(0, 10.0, null, today)
        assertTrue(plans.isEmpty())
    }

    @Test
    fun quantitiesAddUpToStockAndAreAtLeastOne() {
        repeat(1000) { seed ->
            val generator = SeedBatchGenerator(Random(seed))
            for (stock in listOf(1, 3, 4, 15, 16, 100)) {
                val plans = generator.generatePlans(stock, 10.0, null, today)
                assertEquals(stock, plans.sumOf { it.quantity})
                assertTrue(plans.all { it.quantity >= 1 })
            }
        }
    }

    @Test
    fun knownExpirationDateIsUsedForTheFirstBatch() {
        val known = today.plusDays(200)
        val plans = SeedBatchGenerator(Random(7)).generatePlans(20, 10.0, known, today)
        assertEquals(known, plans.first().expirationDate)
    }
}