package com.djedje37.pharmacystockmanager.domain.model

import com.djedje37.pharmacystockmanager.testing.batch
import com.djedje37.pharmacystockmanager.testing.product
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ProductTest {

    @Test
    fun currentStockIsSumOfBatchQuantities() {
        val product = product(
            batches = listOf(
                batch(id = 1, quantity = 10),
                batch(id = 2, quantity = 5)
            )
        )

        // check
        assertEquals(15, product.currentStock)

    }


    @Test
    fun currentStockIsZeroWithoutBatches() {
        val product = product(
            batches = listOf()
        )

        // check
        assertEquals(0, product.currentStock)
    }



    @Test
    fun isLowStockIsTrueWhenStockIsBelowThreshold() {
        val product = product(
            alertThreshold = 5,
            batches = listOf(
                batch(id = 1, quantity = 1),
                batch(id = 2, quantity = 2)
            )
        )

        assertTrue(product.isLowStock)
    }


    @Test
    fun isLowStockIsFalseWhenStockIsAboveThreshold() {
        val product = product(
            alertThreshold = 5,
            batches = listOf(
                batch(id = 1, quantity = 10),
                batch(id = 2, quantity = 2)
            )
        )

        assertFalse(product.isLowStock)
    }

    @Test
    fun isLowStockIsTrueWhenStockIsEqualThreshold() {
        val product = product(
            alertThreshold = 5,
            batches = listOf(
                batch(id = 1, quantity = 3),
                batch(id = 2, quantity = 2)
            )
        )

        assertTrue(product.isLowStock)
    }

    // TODO : is low Stock
}