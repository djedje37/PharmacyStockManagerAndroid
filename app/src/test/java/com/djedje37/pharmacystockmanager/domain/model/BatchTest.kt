package com.djedje37.pharmacystockmanager.domain.model

import com.djedje37.pharmacystockmanager.testing.batch
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.time.LocalDate

class BatchTest {

    private val today = LocalDate.now()


    @Test
    fun isExpiredIsTrueWhenExpirationIsBeforeToday() {
        val batch = batch(
            expirationDate = today.minusDays(1)
        )

        assertTrue(batch.isExpired(today))
    }

    @Test
    fun isExpiredIsFalseWhenExpirationIsAfterIsAfterToday() {
        val batch = batch(
            expirationDate = today.plusDays(1)

        )

        assertFalse(batch.isExpired(today))
    }

    @Test
    fun isExpiredIsFalseWhenExpirationIsToday() {
        val batch = batch(
            expirationDate = today

        )

        assertFalse(batch.isExpired(today))
    }


}