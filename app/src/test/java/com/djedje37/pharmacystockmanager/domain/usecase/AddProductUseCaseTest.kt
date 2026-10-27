package com.djedje37.pharmacystockmanager.domain.usecase

import com.djedje37.pharmacystockmanager.testing.FakeProductRepository
import com.djedje37.pharmacystockmanager.testing.product
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddProductUseCaseTest {

    @Test
    fun invokeWithBlankCipReturnsBlankCipError() = runTest {
        val useCase = AddProductUseCase(FakeProductRepository())

        val result = useCase(product(cip = " "))

        assertEquals(AddProductResult.Failure(AddProductError.BlankCip), result)
    }

    @Test
    fun invokeWithBlankNameReturnsBlankNameError() = runTest {
        val useCase = AddProductUseCase(FakeProductRepository())

        val result = useCase(product(name = " "))

        assertEquals(AddProductResult.Failure(AddProductError.BlankName), result)

    }

    @Test
    fun invokeWithExistingCipReturnsDuplicateCipError() = runTest {
        val useCase = AddProductUseCase(FakeProductRepository(
            listOf(
                product( id = 1, cip = "1", name= "Doliprane")

            )
        ))
        val result = useCase(product(id = 2, cip = "1", name ="Doliprane"))

        assertEquals(AddProductResult.Failure(AddProductError.DuplicateCip), result)

    }

    @Test
    fun invokeWithValidProductSavesTrimmedProduct() = runTest {
        val repository = FakeProductRepository()
        val useCase = AddProductUseCase(repository)

        val result = useCase(
            product(cip = " 123", name = " Doliprane")
        )

        assertEquals(AddProductResult.Success, result)
        val saved = repository.observeProducts().first()
        assertEquals(listOf("123"), saved.map {it.cip})
        assertEquals(listOf("Doliprane"), saved.map {it.name})

    }

}