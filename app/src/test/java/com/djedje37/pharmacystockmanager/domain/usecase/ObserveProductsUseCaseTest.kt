package com.djedje37.pharmacystockmanager.domain.usecase

import app.cash.turbine.test
import com.djedje37.pharmacystockmanager.testing.FakeProductRepository
import com.djedje37.pharmacystockmanager.testing.product
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ObserveProductsUseCaseTest {

    @Test
    // runTest  => call code suspend in test
    fun invokeEmitsProductsSortedByName() = runTest {
        val repository = FakeProductRepository(
            listOf(
                product(id = 1, cip = "1", "Doliprane"),
                product(id = 2, cip = "2", "Aspirine")
            )
        )

        val useCase = ObserveProductsUseCase(repository)

        // useCase().test : turbine listen to flow
        useCase().test {
            // awaitItem listen the first list emission
            val products = awaitItem()
            assertEquals(listOf("Aspirine", "Doliprane"), products.map { it.name})
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun invokeEmitsAgainWhenAProductIsAdded() = runTest {
        val repository = FakeProductRepository(
            listOf(
                product(id = 1, cip = "1", name = "Aspirine")
            )
        )

        val useCase = ObserveProductsUseCase(repository)

        useCase().test {
            // First emission : 1 product
            assertEquals(listOf("Aspirine"), awaitItem().map {
                it.name
            })

            // Add a new product when turnine is listing
            repository.addProduct(product( id = 2, cip = "2", name ="Doliprane"))

            // Second emission : 2 products
            assertEquals(listOf("Aspirine", "Doliprane"), awaitItem().map { it.name })
            cancelAndIgnoreRemainingEvents()

        }
    }
}